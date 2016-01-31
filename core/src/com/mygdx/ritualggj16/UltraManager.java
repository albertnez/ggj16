package com.mygdx.ritualggj16;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class UltraManager
{
    public static boolean isGaemActive = false;

    /** TEXTAQUENS **/
    enum DialogOwner
    {
        PLAYER_1,
        PLAYER_2,
        PLAYER_3
    };

    public static String dialogs[] = {
        "EEHHEHEHEHEHEEHEHE",
        "SEMOS UNOS SATANICOS\nDEL FOQUIN COPONAZO",
            "SI, ESTOY MUY DE ACUERDO\nQUERIDO ASOCIADO",
        "ENGA.. AMOS A REPASAR\nLO CABIA KE ASER",
            "PUES SI MAL NO RECUERDO\nVENIAMOS POR EL TEMA\nDEL [RED]RITUAL[]",

        "AH SI, ER [RED]SACRIFISIO[]",
            "EL [RED]RITUAL[]",
        "KE?",
            "POR MOTIVOS DE INDOLE SUPERIOR\nLA NOMENCLATURA ADECUADA \n" +
                    "PARA EL PROCESO ES [RED]RITUAL[]",
        "ENTONSES NO NOS \nEMPETAMOS A LA CHURRI?",

            "EL SACRIFICIO DE UN EJEMPLAR\nHUMANOIDE DE GENERO FEMENINO\n" +
                    "ES NECESARIO PARA EL PROCESO",
        "OTIA FALE\nPOH ME LA SACO YA",
            "NO TAN RAPIDO!",
            "RECUERDA QUE \n ANTES DE PROCEDER AL [RED]SACRIFICIO[]\n" +
            "TENEMOS QUE ACTIVAR \nTODAS LAS [ORANGE]RUNAS[]",
        "A SIIIII!!!!!!1111\nEMOS DE PIYAR LOS [ORANGE]SIMBOLACOS[]\n" +
                "KE ACTIVAN LOS PIEDROLOS",

            "EN EFECTO... PERO RECUERDA QUE \nEL [BLACK]GRAN DIOS OSCURO[]\n" +
            "NO VA A QUERER COMPARTIR \nSU PODER POR LA CARA",
            "NOS ENVIARA A SUS FIELES [BLUE]LACAYOS[]\n" +
                    "CON INTENCIONES DE ROMPER\n" +
                    "EL SELLO DE [PINK]VIRGINIDAD[]\n DEL SUJETO SACRIFICADO",

                            "SOIS UNOS [RED]PUTOS GILIPOLLAS[]",

            "PERO PERO TIO...\n AMOS HA BER UNA KOSIYA...\n",
            "LESTAMOS REGALANDO AL \n[BLACK]GRAN DIOS OSCURO[]\n" +
            "UNA JAMELGA DEL GRATIX\n",

                "CORRECTO",

            "TOPE DEL BUENRIS",

                "EXACTO",

            "Y EL JIPI COLEGA A CAMNBVIO\nNOS KIERE PELAR PERO BIEN",

                "LAS INTENCIONES DEL\n[BLACK]GRAN DIOS OSCURO[]\n" +
                        "NO ESTAN AL ALCANCE\nDE NUESTRA COMPRENSION",

            "PERO...",

                "SILENCIO MERLUZO\nA CALLAR YA, COJONES\nQUE ME ESTOY CALENTANDO",

                "VAMOS A EMPEZAR \nEL [RED]RITUAL[]",

            "RECUERDA USAR EL STICK DERECHO\nPARA CONTROLAR TU [PURPLE]PODER[]"
    };
    public static DialogOwner dialog_owner[] =
    {
        DialogOwner.PLAYER_1,
        DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,
        DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,

        DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,
        DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,
        DialogOwner.PLAYER_1,

            DialogOwner.PLAYER_2,
        DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_2,
        DialogOwner.PLAYER_1,


            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_3,

            DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_1,

            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_1,
            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_2,
            DialogOwner.PLAYER_2,

    };

    public static float textTimer = 0.0f;
    public static int textIndex = 0;
    public static float textDuration = 2.0f;

    public static boolean nextText()
    {
        textTimer = 0.0f;
        textIndex++;

        return (textIndex != dialogs.length);
    }

    public static String getText()
    {
        String txt = dialogs[textIndex];

        String sub = txt.substring(0,
            Math.min(
                (int)((textTimer/textDuration)*txt.length()),
                txt.length()
            )
        );

        if (sub.lastIndexOf("[") > sub.lastIndexOf("]"))
        {
            sub = sub.substring(0, sub.lastIndexOf("[")-1);
        }

        return sub;
    }

    public static boolean isTextTimerFinished()
    {
        return textTimer > textDuration;
    }
}
