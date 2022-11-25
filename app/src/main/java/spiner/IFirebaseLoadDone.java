package spiner;

import java.util.List;

import Model.ThucDon;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSucces(List<ThucDon> thucDonList);
    void onFirebaseLoadFailed(String message);
}
