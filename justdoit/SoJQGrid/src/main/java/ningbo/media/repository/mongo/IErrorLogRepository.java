package ningbo.media.repository.mongo;

import ningbo.media.domain.ErrorLog;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IErrorLogRepository extends MongoRepository<ErrorLog, String> {

}
