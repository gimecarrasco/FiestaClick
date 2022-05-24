package com.fiestaClick.demo.Enum;

public enum City {
    LAS_HERAS ("Las Heras"), 
    LAVALLE ("Lavalle"), 
    LUJAN ("Luján"),
    RIVADAVIA ("Rivadavia"),
    TUNUYAN ("Tunuyán"), 
    MAIPU ("Maipú"),
    TUPUNGATO ("Tupungato"),
    GODOY_CRUZ ("Godoy Cruz"),
    SAN_CARLOS ("San Carlos"),
    GUAYMALLEN ("Guaymallén"),
    MALARGUE ("Malargüe"),
    JUNIN ("Junín"),
    LA_PAZ ("La Paz"),
    SAN_RAFAEL ("San Rafael"),
    SAN_MARTIN ("San Martin"),
    SANTA_ROSA ("Santa Rosa"),
    GRAL_ALVEAR ("General Alvear"),
    MENDOZA ("Mendoza");
    
    private final String value;

    private City(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
