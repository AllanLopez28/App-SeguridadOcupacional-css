package com.coderplus.css.data.repository

import com.coderplus.css.data.model.ProtocolDetail
import com.coderplus.css.utils.Constants

object ProtocolDetailRepository {

    const val ID_SISMO = "sismo"
    const val ID_INCENDIO = "incendio"
    const val ID_LAB = "laboratorio"
    const val ID_CAMPO = "campo"

    fun getById(id: String): ProtocolDetail? = all.firstOrNull { it.id == id }

    val all: List<ProtocolDetail> = listOf(

        ProtocolDetail(
            id = ID_SISMO,
            title = "Sismo en campus o sedes",
            heroIconUrl = Constants.ICON_MAP,
            resumen = "Actúa rápido para reducir lesiones: agáchate, cúbrete y agárrate. Cuando termine el movimiento, evacúa con orden y llega al punto de reunión seguro.",
            cuandoAplicar = listOf(
                "Cuando sentís temblor dentro de un edificio.",
                "Si hay instrucciones de evacuación posteriores al sismo."
            ),
            pasos = listOf(
                "AGÁCHATE: colócate en manos y rodillas (te ayuda a no caer).",
                "CÚBRETE: protege cabeza y cuello bajo una mesa resistente. Si no hay, cúbrete con los brazos junto a una pared interior.",
                "AGÁRRATE: sostente del refugio hasta que pare el temblor.",
                "Cuando pare: revisa lesiones, aléjate de vidrios/estanterías/cables sueltos.",
                "Evacúa siguiendo la ruta señalizada. No uses elevadores.",
                "Dirígete al punto de reunión seguro y reporta con tu coordinador (pasen lista)."
            ),
            haz = listOf(
                "Identifica antes la ruta de evacuación y el punto de reunión más cercano.",
                "Mantén la calma y ayuda solo si es seguro.",
                "Sigue la señalización y las indicaciones del personal."
            ),
            evita = listOf(
                "No corras durante el temblor.",
                "No te quedes cerca de ventanas o estantes altos.",
                "No bloquees gradas o salidas."
            ),
            notasUca = listOf(
                "En UCA, la señalización te indica rutas de evacuación y a dónde dirigirte en terremotos o incendios. :contentReference[oaicite:0]{index=0}",
                "Ubica el “Punto de reunión seguro” más cercano a tu unidad para saber a dónde dirigirte. :contentReference[oaicite:1]{index=1}",
                "La “Ruta de evacuación” marca el camino más seguro a la salida más cercana. :contentReference[oaicite:2]{index=2}",
                "Las “Escaleras de emergencias” están señalizadas para evacuar el edificio. :contentReference[oaicite:3]{index=3}"
            ),
            contactos = listOf(
                "Emergencias dentro del campus UCA: 2210-6600, extensión 555. :contentReference[oaicite:4]{index=4}"
            )
        ),

        ProtocolDetail(
            id = ID_INCENDIO,
            title = "Incendio en actividades",
            heroIconUrl = Constants.ICON_FIRE,
            resumen = "Prioridad: alertar, evacuar y mantener una vía de escape. Solo usa extintor si el fuego es pequeño y es seguro.",
            cuandoAplicar = listOf(
                "Cuando hay humo, fuego visible, olor a quemado o alarma.",
                "Cuando un conato empieza (pequeño y controlable)."
            ),
            pasos = listOf(
                "Alerta a los demás y notifica a coordinación.",
                "Si es pequeño y tenés salida segura, toma el extintor más cercano.",
                "Colócate a favor del viento en lugares abiertos o cerca de la vía de escape en lugares cerrados.",
                "Quita el seguro (pasador) sin accionar las manijas.",
                "Mantén la distancia adecuada según el extintor.",
                "Presiona las manijas y descarga en vaivén a la BASE del fuego (sin esparcirlo).",
                "Si tu ruta de evacuación se ve amenazada o no es seguro: evacúa inmediatamente y reporta."
            ),
            haz = listOf(
                "Aprende dónde está el extintor más cercano en tu área.",
                "Ten siempre una vía de salida detrás de ti.",
                "Evacúa si hay humo denso o el fuego crece."
            ),
            evita = listOf(
                "No combatas un incendio si no es seguro o no estás capacitado.",
                "No uses agua en fuegos eléctricos o líquidos inflamables.",
                "No te quedes si el fuego corta tu salida."
            ),
            notasUca = listOf(
                "En la UCA hay extintores tipo A, B y C (se identifican por viñetas). :contentReference[oaicite:5]{index=5}",
                "Pasos de uso del extintor (retirar, colocarte cerca de vía de escape, retirar seguro, distancia, sostener, presionar y dirigir a la base). :contentReference[oaicite:6]{index=6}",
                "Si tu ruta de evacuación se ve amenazada y no puedes combatir seguro: abandona y avisa a la ext. 555. :contentReference[oaicite:7]{index=7}"
            ),
            contactos = listOf(
                "Emergencias dentro del campus UCA: 2210-6600, extensión 555. :contentReference[oaicite:8]{index=8}"
            )
        ),

        ProtocolDetail(
            id = ID_LAB,
            title = "Laboratorios y bioseguridad",
            heroIconUrl = Constants.ICON_BEAKER,
            resumen = "Evita exposición: usa EPP, conoce la ducha/lavaojos y sigue el protocolo de derrames. Reportar a tiempo evita que se repita.",
            cuandoAplicar = listOf(
                "Derrames químicos/biológicos, vapores fuertes o salpicaduras.",
                "Contacto con ojos/piel o inhalación de sustancias."
            ),
            pasos = listOf(
                "Detén la actividad y avisa al responsable/instructor.",
                "Colócate el EPP correcto antes de acercarte (guantes, gafas, bata).",
                "Si hay contacto con ojos/piel: lava de inmediato con abundante agua (mínimo 15 min) y busca atención médica.",
                "Aísla el área del derrame y ventila si aplica (sin improvisar).",
                "Sigue la FDS/SDS y el protocolo del laboratorio para limpieza y desecho.",
                "Registra el incidente y notifícalo a coordinación."
            ),
            haz = listOf(
                "Ubica lavaojos/ducha, botiquín y extintor antes de iniciar.",
                "Lávate las manos al quitarte guantes y al salir.",
                "Reporta incidentes y “casi accidentes”."
            ),
            evita = listOf(
                "No mezcles químicos si no estás seguro.",
                "No comas/bebas dentro del laboratorio.",
                "No ocultes incidentes."
            ),
            notasUca = listOf(
                "La señalización del campus también indica dónde está el equipo para controlar emergencias (botiquines, extintores). :contentReference[oaicite:9]{index=9}",
                "Señal del extintor: indica un extintor móvil listo para usarse en caso de incendio. :contentReference[oaicite:10]{index=10}"
            ),
            contactos = listOf(
                "Emergencias dentro del campus UCA: 2210-6600, extensión 555. :contentReference[oaicite:11]{index=11}"
            )
        ),

        ProtocolDetail(
            id = ID_CAMPO,
            title = "Trabajo de campo y visitas",
            heroIconUrl = Constants.ICON_FLAG,
            resumen = "La seguridad en campo empieza con planificación: briefing, roles, check-in/out, EPP, botiquín y comunicación constante.",
            cuandoAplicar = listOf(
                "Salidas a comunidades, visitas técnicas, actividades al aire libre u obras.",
                "Trabajo en zonas desconocidas, con clima/terreno variable."
            ),
            pasos = listOf(
                "Briefing previo: tareas, riesgos, EPP y roles (líder, comunicación, primeros auxilios).",
                "Define ruta, punto de encuentro y horarios; confirma check-in/out con coordinación.",
                "Usa sistema de compañero (buddy system): no trabajar solo.",
                "Lleva botiquín y agua; protege del sol (gorra/bloqueador) si aplica.",
                "Monitorea calor/fatiga: pausa si hay mareo, dolor de cabeza, debilidad o náuseas.",
                "Si ocurre incidente: asegura el área, da primeros auxilios si estás capacitado y reporta de inmediato."
            ),
            haz = listOf(
                "Lleva calzado adecuado (botas) y guantes según tarea.",
                "Mantén el teléfono cargado y un plan de comunicación.",
                "Detén la actividad si el riesgo aumenta."
            ),
            evita = listOf(
                "No separarte del grupo sin avisar.",
                "No tomar atajos por áreas inseguras.",
                "No ignorar señales de golpe de calor o deshidratación."
            ),
            contactos = listOf(
                "Si estás en campus: 2210-6600 ext. 555. :contentReference[oaicite:12]{index=12}"
            )
        )
    )
}