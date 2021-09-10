package com.fabiankevin.parcel.deliverycostcalculator.component.repository;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.entity.ParcelRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRuleRepository extends JpaRepository<ParcelRuleEntity, Long> {
}
