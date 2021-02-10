package com.example.javachallenge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("Willkommen zu meiner bescheidenen Welt des Programmierens");
        showInitialOptions();

        /*
        * Simuliere deine Telefonkontakte und Nachrichtendienste
        *
        *
        *
        * Grüsse den User
        * Zeige diese 3 Optionen: 1. Kontakte verwalten / Manage contacts 2. Nachrichten/Messages   3.Beenden/Schliessen
        *
        * Falls der User 1 auswählt ---> Zeige folgende Optionen:
        *       1. Zeige alle Kontakte
        *       2. Kontakt hinzufügen
        *       3. Kontakt suchen
        *       4. Kontakt löschen
        *       5. Zum vorherigen Menü zurückkehren
        *
        * Falls der User 2 auswählt ---> Zeige folgende Optionen:
        *       1. Zeige die Nachrichtenliste
        *       2. Sende eine neue Nachricht
        *       3. Zum vorherigen Menü zurückkehren
        *
        * Falls der User 3 auswählt ---> Zeige folgende Optionen:
        *       1. Applikation beenden
        *
        * */
    }

    private static void showInitialOptions(){
        System.out.println("Bitte eine Option auswählen: " +
                "\n\t1. Kontakte verwalten" +
                "\n\t2. Nachrichten" +
                "\n\t3. Beenden");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }

    }

    private static void manageMessages() {
        System.out.println("Bitte eine Option auswählen: " +
                "\n\t1. Zeige alle Nachrichten" +
                "\n\t2. Sende einen neue Nachricht" +
                "\n\t3. Zurück");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Wem willst du eine Nachricht senden?");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Bitte den Namen des Kontaktes eingeben:");
            sendNewMessage();
        }else{
            boolean doesExist = false;
            for(Contact c : contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if(doesExist){
                System.out.println("Was willst du übermitteln?");
                String text = scanner.next();
                if(text.equals("")){
                    System.out.println("Bitte eine Nachricht eingeben:");
                    sendNewMessage();
                }else{
                    id++;
                    Message newMessage = new Message(text, name, id);
                    for(Contact c : contacts){
                        if(c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);

                        }
                    }
                }
            }else{
                System.out.println("Dieser Kontakt existiert nicht!");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for(Contact c : contacts){
            allMessages.addAll(c.getMessages());
        }
        if(allMessages.size() > 0){
            for(Message m : allMessages){
                m.getDetails();
                System.out.println("******************************");
            }
        }else{
            System.out.println("Du hast keine Nachrichten");
        }

        showInitialOptions();

    }

    private static void manageContacts(){
        System.out.println("Bitte eine Option auswählen: " +
                "\n\t1. Zeige alle Kontakte" +
                "\n\t2. Neuen Kontakt hinzufügen" +
                "\n\t3. Kontakt suchen" +
                "\n\t4. Kontakt löschen" +
                "\n\t5. Zurück");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContacts();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact() {
        System.out.println("Bitte den Namen eingeben:");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Bitte den Namen eingeben:");
            deleteContact();
        }else{
            boolean doesExist = false;
            Iterator<Contact> i = contacts.iterator();
            while(i.hasNext()){
                Contact c = i.next();
                if(c.getName().equals(name)){
                    doesExist = true;
                    i.remove();
                }
            }

            if(!doesExist){
                System.out.println("Dieser Kontakt existiert nicht.");
            }
        }
        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("Bitte Kontaktnamen eingeben:");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Bitte Kontaktnamen eingeben:");
            searchForContact();
        }else{
            boolean doesExist = false;
            for(Contact c : contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }
            if(!doesExist){
                System.out.println("Dieser Kontakt existiert nicht.");
            }
        }
        showInitialOptions();
    }

    private static void addNewContacts() {
        System.out.println("Neuen Kontakt hinzufügen..." +
                "\nBitte den Kontaktnamen eingeben:");

        String name = scanner.next();

        System.out.println("Bitte Kontaktnummer eingeben:");
        String number = scanner.next();

        System.out.println("Bitte Kontaktemail eingeben:");
        String email = scanner.next();

        if(name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Bitte alle Felder ausfüllen");
            addNewContacts();
        }else{
            boolean doesExist = false;
            for (Contact c : contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("Dieser Kontakt existiert schon! " + name);
                addNewContacts();
            }else{
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println(name + " erfolgreich hinzugefügt.");
            }
        }

        showInitialOptions();
    }

    private static void showAllContacts() {
        if(contacts.size() > 0){
            for(Contact c : contacts){
                c.getDetails();
                System.out.println("*******************************");
            }

            showInitialOptions();
        }else{
            System.out.println("Du hast keine Kontakte");
            showInitialOptions();
        }

    }


}














