module Database {

    requires Engine;
    requires java.persistence;
    requires java.sql;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires org.hibernate.orm.core;

    opens database to com.google.gson, org.hibernate.orm.core;

    exports database;
}