package com.example.program5;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {


    private MutableLiveData<Integer> max = new MutableLiveData<>();
    private MutableLiveData<Integer> min = new MutableLiveData<>();
    private MutableLiveData<Integer> current = new MutableLiveData<>();

    public LiveData<String> getMaxText() {
        return maxText;
    }

    public LiveData<String> getMinText() {
        return minText;
    }

    public LiveData<String> getCurrentText() {
        return currentText;
    }

    private LiveData<String> maxText = Transformations.map(max, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Maximum bottles: "+ input;
        }
    });
    private LiveData<String> minText = Transformations.map(min, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Minimum bottles: "+ input;
        }
    });
    private LiveData<String> currentText = Transformations.map(current, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Current number: "+ input;
        }
    });

    public void changeMax(int max){
        if(max <= 0 || max < min.getValue() ){return;}
        this.max.setValue(max);
    }
    public void changeMin(int min){
        if(min <=0 || min > max.getValue()){return;}
        this.min.setValue(min);
    }
    public boolean incCurrent(){
        if( current.getValue()+1 > max.getValue()){return false;}
        this.current.setValue(current.getValue()+1);
        return true;
    }
    public boolean decCurrent(){
        if( current.getValue()-1 < min.getValue()){return false;}
        this.current.setValue(current.getValue()-1);
        return true;
    }
    public void reset(){
        current.setValue(0);
        min.setValue(0);
        max.setValue(5);
    }

}
