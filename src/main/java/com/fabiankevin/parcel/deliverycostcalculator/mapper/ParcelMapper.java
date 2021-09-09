package com.fabiankevin.parcel.deliverycostcalculator.mapper;

import com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.model.Parcel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParcelMapper {
    Parcel toModel(ParcelBody parcelBody);
}
