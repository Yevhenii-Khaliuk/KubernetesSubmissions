package dev.khaliuk.pingpong.repository;

import dev.khaliuk.pingpong.entity.Ping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PingRepository extends JpaRepository<Ping, Long> {
}
