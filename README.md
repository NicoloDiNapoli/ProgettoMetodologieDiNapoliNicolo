# 📌 HerosOfUniversity

HerosOfUniversity è un gioco di ruolo testuale con interfaccia grafica (JavaFX) ambientato in un'università.
Il giocatore interpreta uno studente che deve affrontare professori e commissioni d'esame come boss,
esplorare aule per raccogliere oggetti, acquistare oggetti e migliorare le proprie abilità per sconfiggere nemici.

---

## 🚀 Come eseguire il progetto

### Prerequisiti
- Java 25/ versione LTS
- Gradle

### Istruzioni

```bash
git clone <url-del-repository>
cd ProgettoMetodologieDiNapoliNicolo
```

### Build del progetto
```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run
```

> Al primo avvio il database viene creato automaticamente con tutti i dati statici di gioco (skill, nemici, stanze, oggetti).

---

## 🤖 Uso di strumenti di AI

Utilizzato Claude (Anthropic) come supporto durante lo sviluppo per:

* comprensione e risoluzione di **errori a runtime**
* supporto nella creazione dell'**interfaccia grafica JavaFX** e nella programmazione ad eventi
* supporto nello sviluppo di metodi per il **bilanciamento del gioco** (logica di combattimento, respawn, restock)
* chiarimenti sulla **divisione delle responsabilità** tra le classi e interfacce

In ogni caso:
* il codice è stato **compreso, discusso e validato** personalmente prima di essere integrato
* le scelte architetturali (nomi classi, package, interfacce) sono state **decise autonomamente**
* l'AI è stata usata come **supporto tecnico**, non come sostituto del ragionamento

---

📌 Per una descrizione più dettagliata dell'uso dell'AI, utilizzare la **Wiki del repository**.

---