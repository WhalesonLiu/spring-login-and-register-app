package com.kgregorczyk.library.service;

import com.kgregorczyk.library.model.NCoV;
import com.kgregorczyk.library.repository.NCoVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class NCoVServiceImpl implements NCoVService {

    static Map<String,String> nCoVKeyMap = new ConcurrentHashMap<>();

    static {
        nCoVKeyMap.put("date","日期");
        nCoVKeyMap.put("newConfirmedCasesNationwide","新增确诊病例(例)-全国");
        nCoVKeyMap.put("newConfirmedCasesHuBei","新增确诊病例(例)-湖北");
        nCoVKeyMap.put("newConfirmedCasesRemark","新增确诊病例(例)-备注");
        nCoVKeyMap.put("newSevereCasesNationwide","新增重症病例(例)-全国");
        nCoVKeyMap.put("newSevereCasesHuBei","新增重症病例(例)-湖北");
        nCoVKeyMap.put("newSevereCasesRemark","新增重症病例(例)-备注");
        nCoVKeyMap.put("newDeathsNationwide","新增死亡病例(例)-全国");
        nCoVKeyMap.put("newDeathsHuBei","新增死亡病例(例)-湖北");
        nCoVKeyMap.put("newDeathsRemark","新增死亡病例(例)-备注");
        nCoVKeyMap.put("newCuredCasesNationwide","新增治愈出院病例(例)-全国");
        nCoVKeyMap.put("newCuredCasesHuBei","新增治愈出院病例(例)-湖北");
        nCoVKeyMap.put("newCuredCasesRemark","新增治愈出院病例(例)-备注");
        nCoVKeyMap.put("newSuspectedCasesNationwide","新增疑似病例(例)-全国");
        nCoVKeyMap.put("newSuspectedCasesHuBei","新增疑似病例(例)-湖北");
        nCoVKeyMap.put("newSuspectedCasesRemark","新增疑似病例(例)-备注");
        nCoVKeyMap.put("beforeDate","截止日期");
        nCoVKeyMap.put("cumulativeConfirmedCases","累计确诊病例(例)-全国");
        nCoVKeyMap.put("cumulativeConfirmedCasesRemark","累计确诊病例(例)-备注");
        nCoVKeyMap.put("severeCase","重症病例-全国");
        nCoVKeyMap.put("severeCaseRemark","重症病例-备注");
        nCoVKeyMap.put("cumulativeDeaths","累计死亡病例-全国");
        nCoVKeyMap.put("cumulativeDeathsRemark","累计死亡病例-备注");
        nCoVKeyMap.put("cumulativeCuredCases","累计治愈出院病例-全国");
        nCoVKeyMap.put("cumulativeCuredCasesRemark","累计治愈出院病例-备注");
        nCoVKeyMap.put("suspectedCase","疑似病例-全国");
        nCoVKeyMap.put("suspectedCaseRemark","疑似病例-备注");
        nCoVKeyMap.put("closeContacts","密切接触者(人)-全国");
        nCoVKeyMap.put("closeContactsRemark","密切接触者(人)-备注");
        nCoVKeyMap.put("dismissed","当日解除医学观察(人)-全国");
        nCoVKeyMap.put("dismissedRemark","当日解除医学观察(人)-备注");
        nCoVKeyMap.put("watching","正在接受医学观察(人)-全国");
        nCoVKeyMap.put("watchingRemark","正在接受医学观察(人)-备注");
        nCoVKeyMap.put("cumulativeConfirmedCasesHongKong","香港特别行政区通报确诊病例(例)");
        nCoVKeyMap.put("cumulativeConfirmedCasesHongKongRemark","香港特别行政区通报确诊病例(例)-备注");
        nCoVKeyMap.put("cumulativeConfirmedCasesMacao","澳门特别行政区通报确诊病例(例)");
        nCoVKeyMap.put("cumulativeConfirmedCasesMacaoRemark","澳门特别行政区通报确诊病例(例)-备注");
        nCoVKeyMap.put("cumulativeConfirmedCasesTaiwan","台湾地区通报确诊病例(例)");
        nCoVKeyMap.put("cumulativeConfirmedCasesTaiwanRemark","台湾地区通报确诊病例(例)-备注");

    }
    private final NCoVRepository nCoVRepository;

    @Autowired
    public NCoVServiceImpl(NCoVRepository nCoVRepository){
        this.nCoVRepository = nCoVRepository;

    }
    @Override
    public NCoV saveNCICase(NCoV NCoV) {
        return nCoVRepository.save(NCoV);
    }

    @Override
    public List<Map<String,Object>> getAll2019_nCoVList(List<String> conditions) {

        List<NCoV> nCoVS = nCoVRepository.findAll();
        return getAll2019_nCoVListByCondition(conditions,nCoVS);

    }

    public List<Map<String,Object>> getAll2019_nCoVListByCondition(List<String> conditions,List<NCoV> nCoVList){
        List<Map<String,Object>> all2019nCoVList = new ArrayList<>();

        try {
            NCoV nCoV = new NCoV();
            Map<String,Object> conMap = new HashMap<>();
            conMap.put("type","line");
            //conMap.put("stack","总量");

            conditions.forEach( fieldName ->{

                Map<String,Object> declaredFieldValueMap = new HashMap<>();

                if(!"serialVersionUID".equals(fieldName) && !"nciId".equals(fieldName)
                        && !"beforeDate".equals(fieldName) ){
                    List<Object> getDeclaredFieldValueList = nCoVList.stream().map(e->
                            {
                                try {
                                    Method getDeclaredMethod = e.getClass().getDeclaredMethod("get" + fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1),null);
                                    Object value = getDeclaredMethod.invoke(e,null);
                                    return value;
                                } catch (NoSuchMethodException ex) {
                                    ex.printStackTrace();
                                } catch (IllegalAccessException ex) {
                                    ex.printStackTrace();
                                } catch (InvocationTargetException ex) {
                                    ex.printStackTrace();
                                }
                                return null;
                            }
                    ).collect(Collectors.toList());

                    if("date".equals(fieldName)){
                        declaredFieldValueMap.put("xAxis",getDeclaredFieldValueList);
                    }else{
                        declaredFieldValueMap.put("name",nCoVKeyMap.get(fieldName));
                        declaredFieldValueMap.putAll(conMap);
                        declaredFieldValueMap.put("data",getDeclaredFieldValueList);
                    }
                    all2019nCoVList.add(declaredFieldValueMap);
                }
            });
        }catch (Exception e){

        }
        return all2019nCoVList;
    }
}
