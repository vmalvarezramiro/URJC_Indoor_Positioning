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
> 
> ## Despliegue Fase3_HA
> Para levantar el entorno tenemos que seguir los siguientes pasos, segun el esquema tenemos las siguientes maquinas
> 
> **1 BBDD**
> 2 FRONTALES WEB
> 1 PROXY
> 
> El os elegido para el entorno de pruebas es ubuntu he descargado la ultima version disponible en la web
> 
> Lo primero en la primera maquina instalamos la bbdd heos elegido mysql(mariadb) el fichero sql que hemos dejado en el repositorio es la bbdd de pruebas que hemos dejado podemos hacer
> un import siguiendo los pasos que se detallan a continuacion
> 
> https://www.stackscale.com/es/blog/importar-exportar-bases-datos-mysql-mariadb/
> 
> una vez importada la bbdd ejecutamos el siguiente comando para configurar la variable global SET GLOBAL time_zone = '+3:00'
> con esto configuramos esta variable con una zona horaria determinada y asi evitamos problemas de configuracion que podamos pasar en en el jar ya que se suele poner que se obtenga
> la fecha de sistema y en mi caso a veces da fallos tambien por formatos españos, ingles etc como es una bbdd de prueba lo configuramos de esta manera.
> La ip de esta maquina es 192.168.1.56
> 
> 
> Despues vamos a levantar los dos frontales web para ello montaremos otras dos maquina y en cada una de ellas instalaremos java para poder ejecutar los jar compilados.
> 
> sudo apt update
> sudo apt install openjdk-8-jdk
> java -version
> 
> Con esto ya hemos instalado la version open de java para ubuntu y lo que tenemos que hacer es subir a cada uno de los frontales los jar que tenemos generados en el repositorio
> una vez subido ejecutamos el siguiente comando para que se levante las instancias, no es necesario instalar ningun sw adicional ya que viene con un tomcat embebido que para 
> nuestro entorno de estudio/proyecto me parece mas que suficiente.
> Una vez subido el jar a la ruta que consideramos ejecutamos el siguiente comando para levantar la instancia
> 
> java -jar indoor_positioning2-0.0.1-SNAPSHOT.jar --server.address=192.168.1.61 --spring.datasource.url=jdbc:mysql://192.168.1.56:3306/indoor_positioning?useSSL=false --spring.datasource.username=usuario --spring.datasource.password=password --spring.jpa.hibernate.ddl-auto=update
> java -jar indoor_positioning2-0.0.1-SNAPSHOT.jar --server.address=192.168.1.62 --spring.datasource.url=jdbc:mysql://192.168.1.56:3306/indoor_positioning?useSSL=false --spring.datasource.username=usuario --spring.datasource.password=password --spring.jpa.hibernate.ddl-auto=update
> 
> Aqui especificamos los siguientes parametros
> 
> --server.address la ip de la propia instancia como vemos en cada instancia ponemos la ip de la maquina
> --spring.datasource.url -> la cadena de conexion donde tenemos alojada la bbdd y el puerto
> --spring.datasource.username=usuario  -> usuario con privilegios sobre la bbdd
> --spring.datasource.password=password -> usuario con privilegios sobre la bbdd
> --spring.jpa.hibernate.ddl-auto=update -> modo apertura de la bbdd para actualizar registros
> 
> Despues debemos levantar la ultima maquina tambien en ubuntu e instalar HA proxy y configurar el proxy para hacer el balance la configuracion que hemos usado se la paso por una captura
> he configurado el round robin y las dos instancias para su balance y ademas he configurado el ssl termination siguiendo las instrucciones que nos indica en los apuntes
> 
> –Generar certificado SSL para HAProxy
> –Configurar HAProxy para usar el certificado y redirigir peticiones HTTP a HTTPS
> –Deshabilitar HTTPS en la aplicación
> 
> https://serversforhackers.com/c/using-ssl-certificates-with-haproxy