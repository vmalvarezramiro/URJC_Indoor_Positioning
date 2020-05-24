> # ***URJC Indoor Positioning***
>
> ## Descripción de la temática
>
> Se trata de una aplicación web para la localización de componentes almacenados en interiores, haciendo uso de la tecnología beacon, mediante balizas o "receptores"
> colocados estratégicamente en el habitáculo
> y "beacons" colocados en los componentes que se deseen tener localizados.
>
> En la parte privada los usuarios podran agregar, modificar o eliminar beacons, componentes o receptores, así como asignar o desvincular beacons a
> componentes y en la parte pública se podrá buscar y localizar
> en un mapa los beacons o componentes deseados.
>
> En la parte del servicio interno lo que se pretende es que el propio servicio interno se encargue de las siguientes funionalidades:
>
> **Envio de Notificaciones:** Se enviaran las notificaciones de los estados y localizacione por mail de los componentes, beacons o receptotres
> **Generacion de Reportes:** Se generaran informes sobre las entidades para el analisis del estado de las mismas.
>
> ## Nombre y descripción de cada entidad
>
> **beacon:** Esta entidad contendrá toda la información referente a los beacons.<br/>
> **component:** Esta entidad contendrá toda la información referente a los componentes.<br/>
> **receiver:** Esta entidad contendrá la posición de las balizas.<br/>
> **history:** Esta entidad recogerá todas las interacciones entre los beacons y las balizas, como timestamp, batería, RSSI... y relacionará a las otras dos.<br/>
>
> ## Integrantes del equipo de desarrollo
>
> **Victor Manuel Alvarez Ramiro**<br/>
>   * Email: vm.alvarezr@alumnos.urjc.es<br/>
>   * Cuenta GitHub: vmalvarezramiro<br/>
>
> **Crístofer Barea Díaz**<br/>
>   * Email: c.barea@alumnos.urjc.es<br/>
>   * Cuenta GitHub: cristofer-barea y EL-NAGASH<br/>
>
> ## Tablero Trello
>  [Tablero URJC Indoor Positioning] (https://trello.com/b/yKEp1LAc/urjc-indoor-positioning)
>
> ## Capturas y descripción
> **Localizador:** Esta es la función principal de la aplicación y también la única accesible de manera pública. Te permite localizar el componente deseado en el plano del lugar.<br/>
> ![Posicion](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/localizador.jpg)
> **Nombre baliza:** Asigna el nombre que quieras al id de cada beacon.<br/>
> ![Nombrar beacons](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/nombre.jpg)
> **Asignar baliza:** Sirve para asignar un beacon a un componente en concreto.<br/>
> ![Asignar beacons](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/asignar.jpg)
> **Desemparejar baliza:** Desparea un beacon del componente al que está asociado.<br/>
> ![Desasignar beacons](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/desemparejar.jpg)
> **Añadir componentes:** Añade componentes, pudiendo también asignarles un beacon específico.<br/>
> ![Añadir componente](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/componente.jpg)
> **Administrar receptores:** En este apartado se le asignará una posición en el plano a cada receptor.<br/>
> ![Manejar receptores](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/receptores.jpg)
> **Información de componentes:** En esta pantalla se mostrarán los componentes, con su respectivo id, beacon asignado y la fecha en que fue localizado por primera y última vez.<br/>
> ![Información de componentes](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/informacion.jpg)
>
> ## Diagrama de navegación
> ![Diagrama de navegacion](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/navegacion.jpg)
>
> ## Modelo de datos
> **Diagrama Entidad/Relación:** .<br/>
> ![Diagrama entidad-relacion](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/entidad-relacion.jpg)
>
> ## Instrucciones para desplegar la aplicación
>  Para desplegar la aplicación será necesario tener un servidor Apache y las herramientas de desarrollo Java instaladas en el servidor, con el servidor Apache iniciado, solo queda ejecutar el archivo "indoor_positioning2-0.0.1-SNAPSHOT.jar" y cargar la base de datos (el archivo "indoor_positioning.sql"). Por último, en cualquier navegador abrir la dirección url: "https://localhost:8443/" para empezar a navegar por la aplicación web. Para acceder a los elementos privados de la aplicación el usuario es: "admin" y la contraseña es: "adminpass" (sin las comillas).<br/>
>
> ## Diagrama Infraestructura HA
> ![Diagrama HA](https://github.com/vmalvarezramiro/URJC_Indoor_Positioning/blob/master/Fase3_HA.png)
