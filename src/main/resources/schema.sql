CREATE TABLE IF NOT EXISTS user_project (
                                            userID SERIAL NOT NULL,
                                            username VARCHAR(40) NOT NULL,
                                            name VARCHAR(40) NOT NULL,
                                            surname VARCHAR(40) NOT NULL,
                                            age INT NOT NULL,
                                            PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS project (
                                       projectID SERIAL NOT NULL,
                                       name VARCHAR(40) NOT NULL,
                                       description VARCHAR(500) NOT NULL,
                                       budget FLOAT NOT NULL,
                                       PRIMARY KEY (projectID)
);

CREATE TABLE IF NOT EXISTS feature (
                                       featureID SERIAL NOT NULL,
                                       name VARCHAR(40) NOT NULL,
                                       description VARCHAR(500) NOT NULL,
                                       deadline DATE NOT NULL,
                                       projectID INT,
                                       PRIMARY KEY (featureID),
                                       FOREIGN KEY (projectID) REFERENCES project(projectID)
);

CREATE TABLE IF NOT EXISTS equipment (
                                         eqID SERIAL NOT NULL,
                                         name VARCHAR(40) NOT NULL,
                                         price FLOAT NOT NULL,
                                         weight FLOAT NOT NULL,
                                         quantity INT NOT NULL,
                                         PRIMARY KEY (eqID)
);

CREATE TABLE IF NOT EXISTS project_user (
                                            projectID INT NOT NULL,
                                            userID INT NOT NULL,
                                            PRIMARY KEY (projectID, userID),
                                            FOREIGN KEY (projectID) REFERENCES project(projectID),
                                            FOREIGN KEY (userID) REFERENCES user_project(userID)
);

CREATE TABLE IF NOT EXISTS project_equipment (
                                                 projectID INT NOT NULL,
                                                 eqID INT NOT NULL,
                                                 PRIMARY KEY (projectID, eqID),
                                                 FOREIGN KEY (projectID) REFERENCES project(projectID),
                                                 FOREIGN KEY (eqID) REFERENCES equipment(eqID)
);
