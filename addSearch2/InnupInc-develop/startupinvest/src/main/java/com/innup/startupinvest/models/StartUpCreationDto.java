package com.innup.startupinvest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class StartUpCreationDto {
    private List<StartUp> startUpList;
}
