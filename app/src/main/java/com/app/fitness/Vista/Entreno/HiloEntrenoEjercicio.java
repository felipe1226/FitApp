package com.app.fitness.Vista.Entreno;

import android.os.AsyncTask;

import static java.lang.Thread.sleep;

public class HiloEntrenoEjercicio extends AsyncTask<Void, Integer, Boolean> {

    Entrenar entrenar;

    AdapterEjercicios adapterEjercicios;

    public boolean entreno;
    public boolean ejercicio;

    private int tiempoTotal;
    private int tiempoEjercicio;

    private int[] cadenaTiempoTotal;
    private int[] cadenaTiempoEjercicio;


    public HiloEntrenoEjercicio(Entrenar entrenar) {
        this.entrenar = entrenar;
    }

    private void initTiempoTotal(){

        tiempoTotal = 0;

        cadenaTiempoTotal = new int[3];
        cadenaTiempoTotal[0] = 0;
        cadenaTiempoTotal[1] = 0;
        cadenaTiempoTotal[2] = 0;

        entreno = true;
    }

    private void initTiempoEjercicio(){

        tiempoEjercicio = 0;

        cadenaTiempoEjercicio = new int[3];
        cadenaTiempoEjercicio[0] = 0;
        cadenaTiempoEjercicio[1] = 0;
        cadenaTiempoEjercicio[2] = 0;

        ejercicio = false;
    }

    public void finalizarEjercicio(){

        initTiempoEjercicio();
    }

    private int[] sumarTiempo(int[] tiempos){
        int[] tiempo = new int[3];
        tiempos[0] = 0;
        tiempos[1] = 0;
        tiempos[2] = 0;

        if(tiempo[2] < 59){ tiempo[2]++; }
        else{
            tiempo[2] = 0;
            if(tiempo[1] < 59){ tiempo[1]++; }
            else{
                tiempo[1] = 0;
                tiempo[0]++;
            }
        }
        return tiempo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        initTiempoTotal();
        initTiempoEjercicio();

        ejercicio = true;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        while (entreno){
            try {
                sleep(1000);
                tiempoTotal ++;

                cadenaTiempoTotal = sumarTiempo(cadenaTiempoTotal);

                if(ejercicio){
                    cadenaTiempoEjercicio = sumarTiempo(cadenaTiempoEjercicio);
                }

                publishProgress(cadenaTiempoTotal[0], cadenaTiempoTotal[1], cadenaTiempoTotal[2],
                        cadenaTiempoEjercicio[0], cadenaTiempoEjercicio[1],cadenaTiempoEjercicio[2]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        String horasTotal = String.valueOf(values[0]);
        String minutosTotal = String.valueOf(values[1]);
        String segundosTotal = String.valueOf(values[2]);

        String horasEjercicio = String.valueOf(values[3]);
        String minutosEjercicio = String.valueOf(values[4]);
        String segundosEjercicio = String.valueOf(values[5]);

        if(values[0]< 10){ horasTotal = "0" + values[0]; }
        if(values[1]< 10){ minutosTotal = "0" + values[1]; }
        if(values[2]< 10){ segundosTotal = "0" + values[2]; }
        if(values[3]< 10){ horasEjercicio = "0" + values[3]; }
        if(values[4]< 10){ minutosEjercicio = "0" + values[4]; }
        if(values[5]< 10){ segundosEjercicio = "0" + values[5]; }

        entrenar.actualizarTiempoTotal(horasTotal, minutosTotal, segundosTotal);

        if(ejercicio){ entrenar.actualizarTiempoEjercicio(horasEjercicio, minutosEjercicio, segundosEjercicio); }
    }

    @Override
    protected void onPostExecute(Boolean resultado) {
        super.onPostExecute(resultado);

        entreno = false;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public void setEjercicio(boolean ejercicio) {
        this.ejercicio = ejercicio;
    }
}
