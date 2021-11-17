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
Inoltre occore indicare anche il giusto path per quanto riguarda i file csv -che troverete nella cartella csv del project explorer da poter prendere e caricarli in locale- e quindi modificare in maniera molto semplice il codice nel package "it.epicode.beservice.commons". All'interno dell'unica classe "Commons" infatti troverete le seguenti diciture a righe 26-27:

  private static final String PATH_PROVINCIA="C:\\Users\\ricca\\Downloads\\province-italiane (1).csv";
	private static final String PATH_COMUNE="C:\\Users\\ricca\\Downloads\\comuni-italiani (1).csv";
  
  Basterà cambiare la parte virgolettata con il percorso in cui avrete i 2 file dopo averli spostati/copiati in locale.
  
  Per il primo funzionamento, prima di startare l'applicazione, al fine di creare il database è necessario scommentare l'annotazione @Component situata all'interno della stessa classe Commons a riga 17 mentre se si vuole ristartare l'applicazione senza dover ricreare il database, occore ricommentare suddetta annotazione.
  
  _______________________________________________________________________________________________________________________________________________________________________________


--------------CORRETTO POPOLAMENTO DATABASE CON CHIAMATE POSTMAN----------------

  
  
	
