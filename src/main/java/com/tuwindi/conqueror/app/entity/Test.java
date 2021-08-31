package com.tuwindi.conqueror.app.entity;

import com.tuwindi.conqueror.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tests")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Test extends BaseEntity {
    private String name;
    private String description;
}
