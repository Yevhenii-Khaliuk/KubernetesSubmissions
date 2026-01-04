package dev.khaliuk.pingpong.repository;

import dev.khaliuk.pingpong.entity.Ping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PingRepository extends JpaRepository<Ping, String> {
    @Query(value = """
            update pings
            set counter = counter + 1
            where id = :id
            returning counter
        """, nativeQuery = true)
    Long incrementAndGet(String id);

    @Modifying
    @Query(value = """
            insert into pings (id, counter)
            values (:id, 0)
            on conflict (id) do nothing
        """, nativeQuery = true)
    void initIfMissing(String id);
}
