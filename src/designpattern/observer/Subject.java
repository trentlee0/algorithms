package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void register(Observer observer);

    void remove(Observer observer);

    void notifyObserver();
}

class WeatherData implements Subject {
    private List<Observer> observers;

    private float temperature;
    private float pressure;
    private float humidity;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        notifyObserver();
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(temperature, pressure, humidity);
        }
    }
}

interface Observer {
    void update(float temperature, float pressure, float humidity);
}

class CurrentConditions implements Observer {
    private float temperature;
    private float pressure;
    private float humidity;

    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    private void display() {
        System.out.println("*** Today temperature: " + temperature + " ***");
        System.out.println("*** Today pressure: " + pressure + " ***");
        System.out.println("*** Today humidity: " + humidity + " ***");
    }
}

class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditions observer = new CurrentConditions();
        weatherData.register(observer);

        System.out.println("===============通知===============");
        weatherData.setData(10.5f, 100.0f, 30.3f);
    }
}
