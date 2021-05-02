package com.example.my_notes.ui.calendar;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.my_notes.DatabaseAdapter;
import com.example.my_notes.Model.Reminder;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public class CalendarViewModel extends ViewModel implements DatabaseAdapter.vmInterface{

    //Atributs
    private MutableLiveData<ArrayList<Reminder>> mReminders;
    private MutableLiveData<String> mToast;
    private DatabaseAdapter da;


    public CalendarViewModel(){
        this.mReminders = new MutableLiveData<>();
        this.mToast = new MutableLiveData<>();
        this.da = new DatabaseAdapter(this);

        //Apareixeran els reminders del dia d'avui
        String today = new CalendarDay().toString();
        Log.d("Dia seleccionat: " + today, "Carregant els reminders del dia seleccionat");
        this.da.getCollectionReminderByUserAndDay(today);
    }


    /**
     * Aquesta funció ens retornaà la llista mReminders, la qual és una MutableLiveData
     * @return llista de reminders
     */
    public LiveData<ArrayList<Reminder>> getReminders(){
        return this.mReminders;
    }


    /**
     * Aquesta funció ens retorna la llista de Toast
     * @return MutableLiveData de Toast.
     */
    public LiveData<String> getToast(){
        return this.mToast;
    }


    /**
     * Aquesta funció ens permet recuperar un element de la llista de reminders.
     *
     * Rep com a parametre una posició
     * @param pos posició que volem obtenir
     *
     * @return Element que ocupa la posició pos
     */
    public Reminder getReminder(int pos) { return this.mReminders.getValue().get(pos);}


    /**
     * La funció permet afegir una nova reminder a la base de dades.
     *
     * Rep com a parametres un titol, una descripció, una data d'alerta en forma de cadena i la data
     * a kla qual es fixarà el reminder (també en cadena).
     *
     * Instanciarà un reminder amb aquestes variables i aquesta s'afegirà a la base de dades.
     *
     * @param title titol del reminder
     * @param description Descripció del reminder
     * @param alert data quan saltarà el reminder
     * @param date data assignada al reminder
     */
    public void addReminder(String title, String description, String alert, String date){
        Reminder reminder = new Reminder(title, description, alert, date);

        if(mReminders.getValue() == null){
            ArrayList<Reminder> rem = new ArrayList<>();
            rem.add(reminder);
            mReminders.setValue(rem);
        }
        else{
            mReminders.getValue().add(reminder);
            mReminders.setValue(mReminders.getValue());
        }
        reminder.saveReminder();
    }


    /**
     * Aquesta funció rep una data en forma de cadena i servirà per a que en el moment de
     * seleccionar una data en el calendari, la base de dades carregui els reminders d'aquell dia.
     *
     * @param date data seleccionada al calendari
     */
    public void remindersDaySelected(String date){
        //Log.d("Dia seleccionat: " + date, "Carregant els reminders del dia seleccionat");
        this.da.getCollectionReminderByUserAndDay(date);
    }


    @Override
    public void setCollection(ArrayList list){
        //Log.d("CalendarViewModel","Ha entrado en el setCollection");
        mReminders.setValue(list);
    }

    @Override
    public void setToast(String s){ mToast.setValue(s); }
}