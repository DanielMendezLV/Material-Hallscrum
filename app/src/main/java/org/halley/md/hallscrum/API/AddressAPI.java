package org.halley.md.hallscrum.API;

/**
 * Created by Mendez Diaz on 18/07/2015.
 */
public class AddressAPI {
    public static final String TOKEN="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyb2lkIiwiaWF0IjoxNDM4MDIxNDA5LCJleHAiOjE0Mzk3NDk0MDl9.OCONOzFPcKeT4M2jjWeaDrR5-Pyjtuv5CUbxhVqJF4I";
    public static final String URL= "192.168.1.6";
    public static final String URL_LOGIN ="http://"+URL+":3000/auth/login";
    public static final String URL_MAKE_AND_ACCOUNT ="http://"+URL+":3000/api/v1/registrar";
    public static final String URL_PROJECTS ="http://"+URL+":3000/api/proyect";
    public static final String URL_PROJECTS_DEL ="http://"+URL+":3000/api/proyect_del";
    public static final String URL_TEAMS ="http://"+URL+":3000/api/equipo";
    public static final String URL_TEAMS_ALT ="http://"+URL+":3000/api/equipo_alternative";
    public static final String URL_META ="http://"+URL+":3000/api/meta";
    public static final String URL_META_INSERT ="http://"+URL+":3000/api/meta_insert";
    public static final String URL_FASES="http://"+URL+":3000/api/fase";
    public static final String URL_FASES_INSERT="http://"+URL+":3000/api/fase_insert";
    public static final String  URL_TEAM_DEL="http://"+URL+":3000/api/equipo_del";
    public static final String URL_META_DEL ="http://"+URL+":3000/api/meta_del";
    public static final String URL_FASES_DEL="http://"+URL+":3000/api/fase_del";


}
