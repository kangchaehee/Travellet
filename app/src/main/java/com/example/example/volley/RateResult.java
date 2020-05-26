package com.example.example.volley;
    /*"rates": {
            "AED": 3.6732,
            "AFN": 75.680375,
            "ALL": 115.910186,
            "AMD": 497.139409,
            "ANG": 1.779762,
            "AOA": 563.895,
            "ARS": 65.296076,
            "AUD": 1.563553,
            "AWG": 1.8,
            "AZN": 1.7025,
            "BAM": 1.790676,
            "BBD": 2,
            "BDT": 84.453868,
            "BGN": 1.788375,
            "BHD": 0.377077,
            "BIF": 1879.429737,
            "BMD": 1,
            "BND": 1.415557,
            "BOB": 6.855579,
            "BRL": 5.2009,
            "BSD": 1,
            "BTC": 0.000147037074,
            "BTN": 75.824739,
            "BWP": 11.936094,
            "BYN": 2.491703,
            "BZD": 1.98851,
            "CAD": 1.38992,
            "CDF": 1700.713609,
            "CHF": 0.965161,
            "CLF": 0.031003,
            "CLP": 853.1,
            "CNH": 7.063997,
            "CNY": 7.0622,
            "COP": 3874.858212,
            "CRC": 569.223514,
            "CUC": 1,
            "CUP": 25.75,
            "CVE": 101.45,
            "CZK": 24.5394,
            "DJF": 178,
            "DKK": 6.8325,
            "DOP": 53.740755,
            "DZD": 127.182076,
            "EGP": 15.7894,
            "ERN": 14.999713,
            "ETB": 32.831695,
            "EUR": 0.915488,
            "FJD": 2.2785,
            "FKP": 0.796083,
            "GBP": 0.796083,
            "GEL": 3.155,
            "GGP": 0.796083,
            "GHS": 5.746866,
            "GIP": 0.796083,
            "GMD": 51.02,
            "GNF": 9483.892132,
            "GTQ": 7.675764,
            "GYD": 207.566223,
            "HKD": 7.751205,
            "HNL": 24.672572,
            "HRK": 6.969,
            "HTG": 94.273189,
            "HUF": 322.058378,
            "IDR": 15734.25,
            "ILS": 3.58169,
            "IMP": 0.796083,
            "INR": 76.199499,
            "IQD": 1186.978864,
            "IRR": 42105,
            "ISK": 142.730015,
            "JEP": 0.796083,
            "JMD": 135.534342,
            "JOD": 0.709,
            "JPY": 107.5603168,
            "KES": 105.955044,
            "KGS": 81.562901,
            "KHR": 4050.156076,
            "KMF": 451.529945,
            "KPW": 900,
            "KRW": 1215.73,
            "KWD": 0.30861,
            "KYD": 0.828588,
            "KZT": 427.908358,
            "LAK": 8907.102984,
            "LBP": 1503.540326,
            "LKR": 189.907127,
            "LRD": 198.125071,
            "LSL": 17.923231,
            "LYD": 1.411889,
            "MAD": 10.208475,
            "MDL": 17.971481,
            "MGA": 3756.891924,
            "MKD": 56.373143,
            "MMK": 1416.838483,
            "MNT": 2783.661132,
            "MOP": 7.940616,
            "MRO": 357,
            "MRU": 37.698277,
            "MUR": 39.511146,
            "MVR": 15.4,
            "MWK": 732.222425,
            "MXN": 23.588956,
            "MYR": 4.332,
            "MZN": 67.095004,
            "NAD": 18.14,
            "NGN": 382.79844,
            "NIO": 33.541775,
            "NOK": 10.312883,
            "NPR": 121.326641,
            "NZD": 1.639995,
            "OMR": 0.384486,
            "PAB": 1,
            "PEN": 3.351964,
            "PGK": 3.461924,
            "PHP": 50.6405,
            "PKR": 166.789338,
            "PLN": 4.16655,
            "PYG": 6441.394128,
            "QAR": 3.620117,
            "RON": 4.42335,
            "RSD": 107.59,
            "RUB": 73.444167,
            "RWF": 946.873763,
            "SAR": 3.760011,
            "SBD": 8.297008,
            "SCR": 16.526596,
            "SDG": 55.3,
            "SEK": 9.960355,
            "SGD": 1.416441,
            "SHP": 0.796083,
            "SLL": 9697.503248,
            "SOS": 575.156344,
            "SRD": 7.458,
            "SSP": 130.26,
            "STD": 22050.693068,
            "STN": 22.6,
            "SVC": 8.700628,
            "SYP": 513.05544,
            "SZL": 17.924238,
            "THB": 32.72124,
            "TJS": 10.176383,
            "TMT": 3.5,
            "TND": 2.89875,
            "TOP": 2.32185,
            "TRY": 6.780272,
            "TTD": 6.71806,
            "TWD": 30.053001,
            "TZS": 2300.740238,
            "UAH": 27.148893,
            "UGX": 3763.277299,
            "USD": 1,
            "UYU": 43.165119,
            "UZS": 9539.470184,
            "VEF": 248487.642241,
            "VES": 88278.48156,
            "VND": 23353.618117,
            "VUV": 124.349032,
            "WST": 2.786846,
            "XAF": 600.520914,
            "XAG": 0.06480902,
            "XAU": 0.00058161,
            "XCD": 2.70255,
            "XDR": 0.72991,
            "XOF": 600.520914,
            "XPD": 0.0004523,
            "XPF": 109.246806,
            "XPT": 0.00130068,
            "YER": 250.300053,
            "ZAR": 18.15292,
            "ZMW": 18.597908,
            "ZWL": 322.000001
            }*/

    //환율 관련
public class RateResult {
    public float AED;
    public float AFN;
    public float ALL;
    public float AMD;
    public float ANG;
    public float AOA;
    public float ARS;
    public float AUD;
    public float AWG;
    public float AZN;
    public float BAM;
    public float BBD;
    public float BDT;
    public float BGN;
    public float BHD;
    public float BIF;
    public float BMD;
    public float BND;
    public float BOB;
    public float BRL;
    public float BSD;
    public float BTC;
    public float BTN;
    public float BWP;
    public float BYN;
    public float BZD;
    public float CAD;
    public float CDF;
    public float CHF;
    public float CLF;
    public float CLP;
    public float CNH;
    public float CNY;
    public float COP;
    public float CRC;
    public float CUC;
    public float CUP;
    public float CVE;
    public float CZK;
    public float DJF;
    public float DKK;
    public float DOP;
    public float DZD;
    public float EGP;
    public float ERN;
    public float ETB;
    public float EUR;
    public float FJD;
    public float FKP;
    public float GBP;
    public float GEL;
    public float GGP;
    public float GHS;
    public float GIP;
    public float GMD;
    public float GNF;
    public float GTQ;
    public float GYD;
    public float HKD;
    public float HNL;
    public float HRK;
    public float HTG;
    public float HUF;
    public float IDR;
    public float ILS;
    public float IMP;
    public float INR;
    public float IQD;
    public float IRR;
    public float ISK;
    public float JEP;
    public float JMD;
    public float JOD;
    public float JPY;
    public float KES;
    public float KGS;
    public float KHR;
    public float KMF;
    public float KPW;
    public float KRW;
    public float KWD;
    public float KYD;
    public float KZT;
    public float LAK;
    public float LBP;
    public float LKR;
    public float LRD;
    public float LSL;
    public float LYD;
    public float MAD;
    public float MDL;
    public float MGA;
    public float MKD;
    public float MMK;
    public float MNT;
    public float MOP;
    public float MRO;
    public float MRU;
    public float MUR;
    public float MVR;
    public float MWK;
    public float MXN;
    public float MYR;
    public float MZN;
    public float NAD;
    public float NGN;
    public float NIO;
    public float NOK;
    public float NPR;
    public float NZD;
    public float OMR;
    public float PAB;
    public float PEN;
    public float PGK;
    public float PHP;
    public float PKR;
    public float PLN;
    public float PYG;
    public float QAR;
    public float RON;
    public float RSD;
    public float RUB;
    public float RWF;
    public float SAR;
    public float SBD;
    public float SCR;
    public float SDG;
    public float SEK;
    public float SGD;
    public float SHP;
    public float SLL;
    public float SOS;
    public float SRD;
    public float SSP;
    public float STD;
    public float STN;
    public float SVC;
    public float SYP;
    public float SZL;
    public float THB;
    public float TJS;
    public float TMT;
    public float TND;
    public float TOP;
    public float TRY;
    public float TTD;
    public float TWD;
    public float TZS;
    public float UAH;
    public float UGX;
    public float USD;
    public float UYU;
    public float UZS;
    public float VEF;
    public float VES;
    public float VND;
    public float VUV;
    public float WST;
    public float XAF;
    public float XAG;
    public float XAU;
    public float XCD;
    public float XDR;
    public float XOF;
    public float XPD;
    public float XPF;
    public float XPT;
    public float YER;
    public float ZAR;
    public float ZMW;
    public float ZWL;
}
