# EpicEnergyService
Progetto finale epicode

L'applicazione prevede di realizzare il backend di un sistema CRM per un'azienda fornitrice di energia, denominata "EPIC ENERGY SERVICES", 
che vuole gestire i contatti con i propri clienti business.

Il sistema, basato su Web Service REST Spring Boot e database PostgreSQL, deve permettere di gestire un elenco di clienti, il quale avrà associato ad ognuno un indirizzo, un comune, una provincia ed un insieme di fatture.
 
 Il back-end deve fornire al front-end tutte le funzioni necessarie a gestire in modo completo (Aggiungere, modificare ed eliminare) i suddetti elementi.
Deve essere possibile ordinare i clienti per diversi fattori ----(troverete tutto nelle chiamate POSTMAN)---- .
 Per gestire in modo efficiente un numero cospicuo di elementi, occorre utilizzare la paginazione.
 Prevedere inoltre un sistema di autenticazione e autorizzazione basato su token JWT che permetta a diversi utenti di accedere alle funzioni del backend e di registrarsi al sistema. 

* Importazione Comuni e Province
Viene fornito un elenco dei comuni in formato CSV (comuni-italiani.csv), che deve essere importato nel sistema per mezzo di una apposita procedura Java da eseguire manualmente per popolare il db. Viene fornito inoltre un secondo file (province-italiane.csv) contenente la corrispondenza tra nome provincia e sigla ed anch'esso deve essere importato ed integrato con le informazioni relative ai comuni.
 Contestualmente alla realizzazione del sistema occorre inoltre:
- Realizzare una collection Postman contenente tutte le chiamate alle API del servizio, comprese quelle di autenticazione
- Implementare i principali test con JUnit

Opzionale:
realizzare un piccolo frontend per il nostro applicativo/api di backend, sfruttando le tecnologie Thymeleaf. 
Realizzare un portalino con delle pagine che permettano l'accesso alle funzioni CRUD e di ricerca sulle entità Cliente, Fattura, Utente, Comune, Provincia.
Per confezionare e adeguare rapidamente l'estetica del portale, sfruttare una libreria come Bootstrap, da inserire nei contenuti static della nostra app 
SpringBoot.

________________________________________________________________________________________________________________________________________________________________________________

 
 -----------------------ISTRUZIONI PER IL CORRETTO FUNZIONAMENTO--------------------------
 
 
L'applicazione per poter funzionare necessita di un collegamento in locale al proprio database nelle applications property, all'interno della cartella resources.
Dopodichè l'applicazione è pronta per essere startata e creare il database che risulterà vuoto.
Fondamentale ricordarsi, che una volta popolato il database, se si vuole ristartare l'applicazione e non perdere i dati inseriti in quest'ultimo, è necessario ritornare nelle applications property e cambiare la dicitura da "create" ad "update" e di conseguenza commentare la dicitura a riga 20 @Component nel package it.epicode.beservice.common all'interno della classe Commons
  
  _______________________________________________________________________________________________________________________________________________________________________________


--------------CORRETTO POPOLAMENTO DATABASE CON CHIAMATE POSTMAN----------------

Ai fini del popolamento del database, all'interno del progetto, è inserita una cartella "Chiamate POSTMAN", in cui sono presenti le collezioni sia per il suddetto popolamento che per tutte le ricerche. Per poter popolare correttamente il database, occorre eseguire in maniera corretta le seguenti chiamate:
1. Save indirizzo
2. Save Cliente
3. Save Stato Fattura
4. Save Fattura

Inoltre, se si vogliono aggiungere al database più Clienti o Fatture, è necessario cambiare alcuni campi che ho reso unici, ovvero in Cliente:
1. ragione sociale 
2. partita iva
3. email
4. pec 

mentre in Fattura:
1. numero

  
  
	
