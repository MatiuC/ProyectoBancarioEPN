-- database: ../database/db_BancoEPN.sqlite
-- Eliminar tablas si existen
DROP TABLE IF EXISTS PersonaRol;

DROP TABLE IF EXISTS Transaccion;

DROP TABLE IF EXISTS Balance;

DROP TABLE IF EXISTS Sesion;

DROP TABLE IF EXISTS Rol;

DROP TABLE IF EXISTS Tarjeta;

DROP TABLE IF EXISTS Persona;

-- Eliminar vistas si existen
DROP VIEW IF EXISTS VistaBalance;

DROP VIEW IF EXISTS VistaTransacciones;

DROP VIEW IF EXISTS VistaSesionActiva;

-- Tabla de Persona
CREATE TABLE IF NOT EXISTS
    Persona (
        persona_id INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre TEXT NOT NULL,
        apellido TEXT NOT NULL,
        fecha_nacimiento TEXT,
        direccion TEXT,
        email TEXT,
        telefono TEXT,
        estado TEXT CHECK (estado IN ('activo', 'inactivo')) DEFAULT 'activo'
    );

-- Tabla de Rol
CREATE TABLE IF NOT EXISTS
    Rol (
        rol_id INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre_rol TEXT NOT NULL
    );

-- Tabla de relación Persona-Rol (para asignar roles a personas)
CREATE TABLE IF NOT EXISTS
    PersonaRol (
        persona_id INTEGER,
        rol_id INTEGER,
        PRIMARY KEY (persona_id, rol_id),
        FOREIGN KEY (persona_id) REFERENCES Persona (persona_id),
        FOREIGN KEY (rol_id) REFERENCES Rol (rol_id)
    );

-- Tabla de Tarjeta
CREATE TABLE IF NOT EXISTS Tarjeta (
    tarjeta_id INTEGER PRIMARY KEY AUTOINCREMENT,
    persona_id INTEGER,
    numero_tarjeta TEXT UNIQUE NOT NULL,
    fecha_expiracion TEXT NOT NULL,
    pin TEXT NOT NULL,
    saldo DECIMAL(10, 2) DEFAULT 0,
    estado TEXT CHECK (estado IN ('activa', 'bloqueada')) DEFAULT 'activa',
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN NOT NULL DEFAULT 1,
    FOREIGN KEY (persona_id) REFERENCES Persona (persona_id)
);


-- Tabla de Sesion
CREATE TABLE IF NOT EXISTS
    Sesion (
        sesion_id INTEGER PRIMARY KEY AUTOINCREMENT,
        persona_id INTEGER,
        usuario TEXT,
        contrasenia TEXT,
        fecha_inicio TEXT DEFAULT CURRENT_TIMESTAMP,
        fecha_fin TEXT,
        ip_origen TEXT,
        estado TEXT CHECK (estado IN ('activa', 'cerrada')) DEFAULT 'activa',
        fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        activo BOOLEAN NOT NULL DEFAULT 1,
        FOREIGN KEY (persona_id) REFERENCES Persona (persona_id)
    );

-- Tabla de Balance
CREATE TABLE IF NOT EXISTS
    Balance (
        balance_id INTEGER PRIMARY KEY AUTOINCREMENT,
        persona_id INTEGER,
        balance_total DECIMAL(15, 2) DEFAULT 0,
        fecha_actualizacion TEXT DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (persona_id) REFERENCES Persona (persona_id)
    );

-- Tabla de Transaccion
CREATE TABLE IF NOT EXISTS Transaccion (
    transaccion_id INTEGER PRIMARY KEY AUTOINCREMENT,
    origen_tarjeta_id INTEGER,
    destino_tarjeta_id INTEGER,
    monto DECIMAL(10, 2) NOT NULL,
    tipo_transaccion TEXT CHECK (
        tipo_transaccion IN ('deposito', 'retiro', 'transferencia', 'consumo')
    ) NOT NULL,
    fecha_transaccion TEXT DEFAULT CURRENT_TIMESTAMP,
    fechaCreacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaModificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN NOT NULL DEFAULT 1,
    FOREIGN KEY (origen_tarjeta_id) REFERENCES Tarjeta (tarjeta_id),
    FOREIGN KEY (destino_tarjeta_id) REFERENCES Tarjeta (tarjeta_id)
);


-- Vista para mostrar los balances de los clientes
CREATE VIEW IF NOT EXISTS
    VistaBalance AS
SELECT
    p.persona_id,
    p.nombre,
    p.apellido,
    b.balance_total
FROM
    Persona p
    JOIN Balance b ON p.persona_id = b.persona_id;

-- Vista para mostrar las transacciones de un cliente
CREATE VIEW IF NOT EXISTS
    VistaTransacciones AS
SELECT
    t.transaccion_id,
    t.origen_tarjeta_id,
    t.destino_tarjeta_id,
    t.monto,
    t.tipo_transaccion,
    t.fecha_transaccion
FROM
    Transaccion t
    JOIN Tarjeta tar ON t.origen_tarjeta_id = tar.tarjeta_id;

-- Vista para consultar detalles de la sesión activa de un usuario
CREATE VIEW IF NOT EXISTS
    VistaSesionActiva AS
SELECT
    p.persona_id,
    p.nombre,
    p.apellido,
    s.fecha_inicio,
    s.estado
FROM
    Sesion s
    JOIN Persona p ON s.persona_id = p.persona_id
WHERE
    s.estado = 'activa';
