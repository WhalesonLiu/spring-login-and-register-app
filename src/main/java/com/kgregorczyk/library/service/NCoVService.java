package com.kgregorczyk.library.service;

import com.kgregorczyk.library.model.NCoV;

import java.util.List;
import java.util.Map;

public interface NCoVService {

    NCoV saveNCICase(NCoV NCoV);

    List<Map<String,Object>> getAll2019_nCoVList(List<String> conditions);
}
