package ningbo.media.bean.enums;

public enum FriendType {
	FANS(0), FOLLOWED(1);

	private Integer value;

	FriendType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	
}
