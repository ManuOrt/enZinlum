#  EnZinlum
## Introducción
Rick va a organizar un concierto de Los Ricknillos y quiere poner a la venta 100 entradas en nuestra plataforma EnZinIum.

Para ello va a utilizar un contrato inteligente construido sobre nuestra plataforma.

Este contrato recibirá instrucciones sobre a quién vender las entradas.

El contrato almacenará que usuarios/as de la plataforma poseen las entradas.

Cada usuario/a dipone de una Address /dirección en la plataforma, desde la que gestionar sus EnZinIums y las entradas.

## Funcionalidades

#### GenSig
- Nos permite crear un par de clave pública y privada


#### Address
- Nos permite generar el par de claves
- Añadir EZI al balance
- Transferir EZIS a la dirección
- Transferir los EZIS de la dirección a un contrato inteligente


#### Token Contract
- Nos permite devolver el nombre en forma _human-readable_.
- Devolver el nombre del símbolo del token de forma _human-readable_.
- Devolver el número de tokens de un propietario.
- Devolver el total de unidades de este token que actualmente existen.
- Transferir tokens del propietario del contrato a la dirección de destino.
- Mostrar en consola todos los propietarios.
- Enviar tokens a la dirección recipient y envía los EnZiniums al propietario del contrato.

## Prerequisitos
- Java 11
- Maven


## Instalación

Para instalar las dependencias de maven ejecuta:
```
mvn install
```

## Créditos

Este proyecto a sido creado por Manuel Ortega.

## Contacto
Github: https://github.com/ManuOrt/enZinlum

Email: mortegamarti@cifpfbmoll.eu


