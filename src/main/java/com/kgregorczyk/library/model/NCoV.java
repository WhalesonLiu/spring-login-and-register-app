package com.kgregorczyk.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * New coronavirus infection
 * 新型冠状病毒感染
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_nci",indexes = {@Index(columnList = "date", unique = true)})
public class NCoV implements Serializable {

    private static final long serialVersionUID = 768193107573563218L;

    @Id
    @GeneratedValue
    private Long nciId;

    //当前录入信息日期
    @Temporal(TemporalType.DATE)
    private Date date;

    //新增确诊病例-全国
    private Integer newConfirmedCasesNationwide;

    //新增确诊病例-湖北
    private Integer newConfirmedCasesHuBei;

    //新增确诊病例-备注
    private String newConfirmedCasesRemark;

    //新增重症病例-全国
    private Integer newSevereCasesNationwide;

    //新增重症病例-湖北
    private Integer newSevereCasesHuBei;

    //新增重症病例-备注
    private String newSevereCasesRemark;

    //新增死亡病例-全国
    private Integer newDeathsNationwide;
    //新增死亡病例-湖北
    private Integer newDeathsHuBei;
    //新增死亡病例-备注
    private String newDeathsRemark;

    //新增治愈出院病例-全国
    private Integer newCuredCasesNationwide;
    //新增治愈出院病例-湖北
    private Integer newCuredCasesHuBei;
    //新增治愈出院病例-备注
    private String newCuredCasesRemark;

    //新增疑似病例-全国
    private Integer newSuspectedCasesNationwide;
    //新增疑似病例-湖北
    private Integer newSuspectedCasesHuBei;
    //新增疑似病例-备注
    private String newSuspectedCasesRemark;

    //截止日期
    @Temporal(TemporalType.DATE)
    private Date beforeDate;

    //累计确诊病例(例)
    private Integer cumulativeConfirmedCases;

    //累计确诊病例(例)-备注
    private String cumulativeConfirmedCasesRemark;

    //重症病例
    private Integer severeCase;
    //重症病例-备注
    private String severeCaseRemark;

    //累计死亡病例
    private Integer cumulativeDeaths;

    //累计死亡病例-备注
    private String cumulativeDeathsRemark;

    //累计治愈出院病例
    private Integer cumulativeCuredCases;

    //累计治愈出院病例-备注
    private String cumulativeCuredCasesRemark;

    //疑似病例
    private Integer suspectedCase;
    //疑似病例-备注
    private String suspectedCaseRemark;

    //密切接触者(人)
    private Integer closeContacts;
    //密切接触者(人)-备注
    private String closeContactsRemark;

    //当日解除医学观察(人)
    private Integer dismissed;

    //当日解除医学观察(人)-备注
    private String dismissedRemark;

    //正在接受医学观察(人)
    private Integer watching;

    //正在接受医学观察(人)-备注
    private String watchingRemark;

    //香港特别行政区通报确诊病例
    private Integer cumulativeConfirmedCasesHongKong;
    //香港特别行政区通报确诊病例-备注
    private String cumulativeConfirmedCasesHongKongRemark;

    //澳门特别行政区通报确诊病例
    private Integer cumulativeConfirmedCasesMacao;
    //澳门特别行政区通报确诊病例-备注
    private String cumulativeConfirmedCasesMacaoRemark;

    //台湾地区通报确诊病例
    private Integer cumulativeConfirmedCasesTaiwan;
    //台湾地区通报确诊病例-备注
    private String cumulativeConfirmedCasesTaiwanRemark;

}
