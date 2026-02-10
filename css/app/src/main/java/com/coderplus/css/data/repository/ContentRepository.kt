package com.coderplus.css.data.repository

import com.coderplus.css.data.model.CardItem
import com.coderplus.css.data.model.HeroSlide
import com.coderplus.css.data.model.ReporteItem
import com.coderplus.css.utils.Constants

object ContentRepository {

    // Hero Slides
    val heroSlides = listOf(
        HeroSlide(Constants.HERO_IMAGE_1, "Campus UCA laboratorios"),
        HeroSlide(Constants.HERO_IMAGE_2, "Campus UCA martires"),
        HeroSlide(Constants.HERO_IMAGE_3, "Cancha campus UCA"),
        HeroSlide(Constants.HERO_IMAGE_4, "Campus UCA 4")
    )

    val heroTitle = "Seguridad Ocupacional en CSS UCA"
    val heroSubtitle = "Informate acerca de la importancia de la seguridad ocupacional y protocolos a seguir en los proyectos del Centro de Servicio Social: identificar peligros, usar el EPP correcto y actuar a tiempo para que las actividades estudiantiles se realicen sin incidentes."

    // Importancia items
    val importanciaItems = listOf(
        CardItem(
            iconUrl = Constants.ICON_SHIELD_CHECK,
            iconAlt = "Escudo de protección",
            title = "Actividades seguras",
            description = "En cada proyecto del servicio social revisamos tareas, herramientas y entorno para prevenir lesiones y proteger a estudiantes y beneficiarios."
        ),
        CardItem(
            iconUrl = Constants.ICON_SHIELD_EXCLAMATION,
            iconAlt = "Alerta de bioseguridad",
            title = "EPP y bioseguridad",
            description = "Se define el EPP según la actividad (guantes, mascarilla, lentes, calzado) y se refuerzan prácticas de higiene para evitar contagios o exposición a agentes."
        ),
        CardItem(
            iconUrl = Constants.ICON_MAP,
            iconAlt = "Mapa y rutas",
            title = "Rutas y puntos de reunión",
            description = "Antes de iniciar, se indican salidas, zonas seguras y responsables de evacuación en campus o sedes externas donde participen estudiantes."
        ),
        CardItem(
            iconUrl = Constants.ICON_MEGAPHONE,
            iconAlt = "Megáfono de reporte",
            title = "Reportar a tiempo",
            description = "Reportar condiciones inseguras, incidentes y casi accidentes permite activar protocolos, atender a quien lo necesite y evitar recurrencia."
        )
    )

    // Qué es items
    val queEsItems = listOf(
        CardItem(
            iconUrl = Constants.ICON_CLIPBOARD_CHECK,
            iconAlt = "Evaluación de riesgos",
            title = "Evaluación de riesgos en proyectos",
            description = "Antes de cada actividad del Centro de Servicio Social se identifican peligros, se clasifican los riesgos y se definen controles claros."
        ),
        CardItem(
            iconUrl = Constants.ICON_SHIELD_CHECK,
            iconAlt = "EPP y control",
            title = "Controles y EPP adecuados",
            description = "Se elige el EPP según la tarea (guantes, lentes, mascarilla, calzado) y se refuerzan controles administrativos y de ingeniería."
        ),
        CardItem(
            iconUrl = Constants.ICON_CHAT_BUBBLE,
            iconAlt = "Comunicación",
            title = "Comunicación y roles claros",
            description = "Cada equipo conoce responsables, rutas de evacuación, contactos de emergencia y cómo reportar condiciones inseguras."
        )
    )

    // Protocolos items
    val protocolosItems = listOf(
        CardItem(
            iconUrl = Constants.ICON_MAP,
            iconAlt = "Sismo y evacuación",
            title = "Sismo en campus o sedes",
            description = "Alarma: protégete-cúbrete-agárrate, corta calor/gas si es seguro. Evacúa a punto de reunión y pasa lista del equipo y voluntarios."
        ),
        CardItem(
            iconUrl = Constants.ICON_FIRE,
            iconAlt = "Incendio",
            title = "Incendio en actividades",
            description = "Activa alarma, corta energía si procede. Usa extintor solo con ruta de escape y apoyo; evacúa a zona segura y reporta a coordinación/911."
        ),
        CardItem(
            iconUrl = Constants.ICON_BEAKER,
            iconAlt = "Laboratorio y bioseguridad",
            title = "Laboratorios y bioseguridad",
            description = "Bata, guantes y gafas; conocer duchas/lavaojos. FDS a la vista; derrames: aislar, ventilar y notificar al responsable del laboratorio."
        ),
        CardItem(
            iconUrl = Constants.ICON_FLAG,
            iconAlt = "Trabajo de campo",
            title = "Trabajo de campo y visitas",
            description = "Briefing previo, ruta y punto de encuentro, check-in/out con coordinación. Kit de primeros auxilios y EPP según terreno y clima."
        )
    )

    // EPP items
    val eppItems = listOf(
        CardItem(
            iconUrl = Constants.ICON_SHIELD_CHECK,
            iconAlt = "Casco y protección",
            title = "Cabeza y rostro",
            description = "Casco con barbuquejo, lentes o careta; revisar daños y fecha; ajustar arnés para evitar desprendimientos."
        ),
        CardItem(
            iconUrl = Constants.ICON_HAND_RAISED,
            iconAlt = "Guantes",
            title = "Manos",
            description = "Guantes según riesgo: látex/nitrilo (bio), cuero (mecánico), dieléctricos (eléctrico). Retirar y desechar según protocolo."
        ),
        CardItem(
            iconUrl = Constants.ICON_SHIELD_EXCLAMATION,
            iconAlt = "Calzado de seguridad",
            title = "Pies",
            description = "Botas con punta y plantilla de protección en campo/obra; suela antideslizante en laboratorio; revisar sujeción y desgaste."
        ),
        CardItem(
            iconUrl = Constants.ICON_FACE_SMILE,
            iconAlt = "Respiración y bioseguridad",
            title = "Respiración y bioseguridad",
            description = "Mascarilla quirúrgica o KN95 según entorno; recambio cuando esté húmeda o dañada; higiene de manos antes y después de usarla."
        )
    )

    // Riesgos items
    val riesgosItems = listOf(
        CardItem(
            iconUrl = Constants.ICON_EXCLAMATION_TRIANGLE,
            iconAlt = "Riesgos físicos",
            title = "Físicos",
            description = "Caídas al mismo y distinto nivel, golpes con objetos, ruido, calor y frío en actividades de campo o campus."
        ),
        CardItem(
            iconUrl = Constants.ICON_BEAKER,
            iconAlt = "Riesgos químicos",
            title = "Químicos",
            description = "Manipulación de reactivos o productos de limpieza: vapores, derrames, contacto con piel y ojos. FDS y ventilación obligatoria."
        ),
        CardItem(
            iconUrl = Constants.ICON_BUG_ANT,
            iconAlt = "Riesgos biológicos",
            title = "Biológicos",
            description = "Exposición a fluidos o superficies contaminadas en visitas comunitarias o laboratorio. Uso de guantes/mascarilla y desinfección."
        ),
        CardItem(
            iconUrl = Constants.ICON_USER_GROUP,
            iconAlt = "Ergonómicos y psicosociales",
            title = "Ergonómicos y psicosociales",
            description = "Posturas prolongadas, carga manual de materiales, jornadas extensas. Planificar pausas, alternar tareas y pedir apoyo."
        )
    )

    // Reportes
    val reportesDescription = "Reportar a tiempo permite activar ayuda, documentar causas y prevenir que se repita. Si ocurre un evento, sigue estos pasos y avisa a tu coordinador."

    val reportesTipos = listOf(
        ReporteItem(
            tipo = "Condición insegura",
            descripcion = "cable suelto, derrame, señalización ausente. Detén la actividad si hay riesgo."
        ),
        ReporteItem(
            tipo = "Incidente",
            descripcion = "evento sin lesión grave. Registra fecha, lugar, tarea y quienes estaban presentes."
        ),
        ReporteItem(
            tipo = "Accidente",
            descripcion = "hay lesión o daño. Brinda primeros auxilios, activa 911 si es necesario y notifica al responsable del CSS."
        ),
        ReporteItem(
            tipo = "Documentación",
            descripcion = "Documenta con fotos y contacta a tu coordinador para el registro formal."
        )
    )
}
