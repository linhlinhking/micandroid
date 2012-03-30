package org.paramecium.aop.cglib;

import org.paramecium.aop.asm.Label;
import org.paramecium.aop.asm.MethodAdapter;
import org.paramecium.aop.asm.MethodVisitor;
import org.paramecium.aop.asm.Opcodes;
import org.paramecium.aop.asm.Type;

public class LocalVariablesSorter extends MethodAdapter {

	private static class State {
		int[] mapping = new int[40];
		int nextLocal;
	}

	protected final int firstLocal;
	private final State state;

	public LocalVariablesSorter(final int access, final String desc,
			final MethodVisitor mv) {
		super(mv);
		state = new State();
		Type[] args = Type.getArgumentTypes(desc);
		state.nextLocal = ((Opcodes.ACC_STATIC & access) != 0) ? 0 : 1;
		for (int i = 0; i < args.length; i++) {
			state.nextLocal += args[i].getSize();
		}
		firstLocal = state.nextLocal;
	}

	public LocalVariablesSorter(LocalVariablesSorter lvs) {
		super(lvs.mv);
		state = lvs.state;
		firstLocal = lvs.firstLocal;
	}

	public void visitVarInsn(final int opcode, final int var) {
		int size;
		switch (opcode) {
		case Opcodes.LLOAD:
		case Opcodes.LSTORE:
		case Opcodes.DLOAD:
		case Opcodes.DSTORE:
			size = 2;
			break;
		default:
			size = 1;
		}
		mv.visitVarInsn(opcode, remap(var, size));
	}

	public void visitIincInsn(final int var, final int increment) {
		mv.visitIincInsn(remap(var, 1), increment);
	}

	public void visitMaxs(final int maxStack, final int maxLocals) {
		mv.visitMaxs(maxStack, state.nextLocal);
	}

	public void visitLocalVariable(final String name, final String desc,
			final String signature, final Label start, final Label end,
			final int index) {
		mv.visitLocalVariable(name, desc, signature, start, end, remap(index));
	}

	// -------------

	protected int newLocal(final int size) {
		int var = state.nextLocal;
		state.nextLocal += size;
		return var;
	}

	private int remap(final int var, final int size) {
		if (var < firstLocal) {
			return var;
		}
		int key = 2 * var + size - 1;
		int length = state.mapping.length;
		if (key >= length) {
			int[] newMapping = new int[Math.max(2 * length, key + 1)];
			System.arraycopy(state.mapping, 0, newMapping, 0, length);
			state.mapping = newMapping;
		}
		int value = state.mapping[key];
		if (value == 0) {
			value = state.nextLocal + 1;
			state.mapping[key] = value;
			state.nextLocal += size;
		}
		return value - 1;
	}

	private int remap(final int var) {
		if (var < firstLocal) {
			return var;
		}
		int key = 2 * var;
		int value = key < state.mapping.length ? state.mapping[key] : 0;
		if (value == 0) {
			value = key + 1 < state.mapping.length ? state.mapping[key + 1] : 0;
		}
		if (value == 0) {
			throw new IllegalStateException("Unknown local variable " + var);
		}
		return value - 1;
	}
}
