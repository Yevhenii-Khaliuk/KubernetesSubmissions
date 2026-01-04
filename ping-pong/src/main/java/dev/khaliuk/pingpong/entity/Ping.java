package dev.khaliuk.pingpong.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ping {
    @Id
    private String id;

    @Column
    private Long counter;
}
