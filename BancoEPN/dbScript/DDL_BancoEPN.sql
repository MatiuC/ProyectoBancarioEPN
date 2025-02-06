-- database: ../database/db_BancoEPN.sqlite
DROP TABLE IF EXISTS Transacciones;
DROP TABLE IF EXISTS TipoTransaccion;
DROP TABLE IF EXISTS Tarjeta;
DROP TABLE IF EXISTS TipoTarjeta;
DROP TABLE IF EXISTS Franquicia;
DROP TABLE IF EXISTS credenciales;
DROP TABLE IF EXISTS cuentaCredito;
DROP TABLE IF EXISTS CuentaBancaria;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Rol;
DROP TABLE IF EXISTS Ciudad;
DROP TABLE IF EXISTS EstadoCivil;
CREATE TABLE EstadoCivil (
    id_estado_civil INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_estado_civil TEXT NOT NULL
);
CREATE TABLE Ciudad (
    id_ciudad INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_ciudad VARCHAR(20) NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A'
);
CREATE TABLE Rol (
    id_rol INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_rol VARCHAR(20) NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A'
);
CREATE TABLE Persona (
    Id_persona INTEGER PRIMARY KEY AUTOINCREMENT,
    cedula VARCHAR(10) NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    sexo VARCHAR(15) NOT NULL,
    estado_civil VARCHAR(15) NOT NULL REFERENCES EstadoCivil (id_estado_civil),
    ciudad VARCHAR(30) NOT NULL REFERENCES Ciudad (id_ciudad),
    edad VARCHAR(3) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    Rol NOT NULL REFERENCES Rol (nombre_rol)
);
CREATE TABLE CuentaBancaria (
    id_cuentabancaria INTEGER PRIMARY KEY AUTOINCREMENT,
    numeroCuenta VARCHAR(8) NOT NULL,
    id_persona INTEGER NOT NULL REFERENCES Persona (Id_persona),
    saldo FLOAT NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A'
);
CREATE TABLE cuentaCredito (
    id_cuentaCredito INTEGER PRIMARY KEY AUTOINCREMENT,
    numeroCuenta VARCHAR(8) NOT NULL,
    id_persona INTEGER NOT NULL REFERENCES Persona (Id_persona),
    saldo_usado FLOAT NOT NULL,
    limiteCredito FLOAT NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A'
);
CREATE TABLE credenciales (
    id_credenciales INTEGER PRIMARY KEY AUTOINCREMENT,
    id_persona INTEGER NOT NULL UNIQUE,
    usuario VARCHAR(15) NOT NULL,
    pass VARCHAR(15) NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT fk_persona FOREIGN KEY (id_persona) REFERENCES Persona (Id_persona) ON UPDATE CASCADE
);
CREATE TABLE Franquicia (
    id_franquicia INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_franquicia VARCHAR(20) NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A'
);
CREATE TABLE TipoTarjeta (
    id_tipo_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_tipo_tarjeta VARCHAR(20) NOT NULL
);
CREATE TABLE Tarjeta (
    Id_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT,
    numero_tarjeta VARCHAR(16) NOT NULL,
    fecha_expedicion DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    tipo_tarjeta VARCHAR(20) NOT NULL REFERENCES TipoTarjeta (id_tipo_tarjeta),
    franquicia VARCHAR(20) NOT NULL REFERENCES Franquicia (id_franquicia),
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    Persona NOT NULL REFERENCES Persona (Id_persona),
    id_cuentabancaria NOT NULL REFERENCES CuentaBancaria (id_cuentabancaria)
);
CREATE TABLE TipoTransaccion (
    id_tipo_transaccion INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_tipo_transaccion VARCHAR(20) NOT NULL
);
CREATE TABLE Transacciones (
    Id_transaccion INTEGER PRIMARY KEY AUTOINCREMENT,
    Origen INTEGER NOT NULL REFERENCES CuentaBancaria (id_cuentabancaria),
    Destino INTEGER NOT NULL REFERENCES CuentaBancaria (id_cuentabancaria),
    Monto FLOAT NOT NULL,
    Fecha DATE NOT NULL,
    Hora TIME NOT NULL,
    TipoTransaccion VARCHAR(15) NOT NULL REFERENCES TipoTransaccion (id_tipo_transaccion),
    Descripcion TEXT NOT NULL,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A'
)