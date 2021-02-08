package com.divi.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class Tododata {
    private static Tododata instance = new Tododata();
    private static String filename = "todoitemlist.txt";

//    private List<Todoitem> todoitems;
    private ObservableList<Todoitem> todoitems;
    private DateTimeFormatter formatter;

    public static Tododata getInstance() {
        return instance;
    }

    private Tododata() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public ObservableList<Todoitem> getTodoitems() {
        return todoitems;
    }
//
//    public void setTodoitems(ObservalbleList<Todoitem> todoitems) {
//        this.todoitems = todoitems;
//    }

   public void addTodoItem(Todoitem item){
        todoitems.add(item);
   }

    public void loadTodoitem() throws IOException {
        todoitems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescritpion = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];
                LocalDate date = LocalDate.parse(dateString,formatter);
                Todoitem item = new Todoitem(shortDescritpion,details,date);
                todoitems.add(item);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeTodoitem() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            Iterator<Todoitem> itr = todoitems.iterator();
            while (itr.hasNext()) {
                Todoitem item = itr.next();
                bw.write(String.format("%s\t%s\t%s\t",
                        item.getShort_description(),
                        item.getDetails(),
                        item.getDeadline()));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void deleteTodoitem(Todoitem item){
        todoitems.remove(item);
    }
}