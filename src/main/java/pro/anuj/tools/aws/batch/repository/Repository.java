package pro.anuj.tools.aws.batch.repository;

import lombok.NonNull;

public interface Repository<E> {

    int count();

    E find(@NonNull String name);

    E findByArn(@NonNull String arn);

    E delete(@NonNull String name);

    E deleteByArn(@NonNull String arn);

    String create(E entity);

    String update(E entity);

}
