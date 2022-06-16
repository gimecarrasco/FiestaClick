package com.fiestaClick.demo.enumerations;

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
    
    private final String name;

    private City(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
