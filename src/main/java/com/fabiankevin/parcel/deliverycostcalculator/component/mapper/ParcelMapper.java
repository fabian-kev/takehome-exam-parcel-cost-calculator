package com.fabiankevin.parcel.deliverycostcalculator.component.mapper;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Parcel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParcelMapper {
    Parcel toModel(ParcelBody parcelBody);
}
