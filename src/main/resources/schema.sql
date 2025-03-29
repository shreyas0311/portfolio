CREATE TABLE
IF NOT EXISTS admin
(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username VARCHAR
(255) NOT NULL,
    password VARCHAR
(255) NOT NULL
);

CREATE TABLE
IF NOT EXISTS projects
(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    project_name VARCHAR
(100) NOT NULL,
    description TEXT NOT NULL,
    project_link VARCHAR
(255),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    duration VARCHAR
(50) NOT NULL,
    skills VARCHAR
(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON
UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS message (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);