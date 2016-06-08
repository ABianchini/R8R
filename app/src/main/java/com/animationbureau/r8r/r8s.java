package com.animationbureau.r8r;

/**
 * Created by anima on 6/6/2016.
 */
public class R8s {
    int _id;
    String _what;
    int _r8;
    String _why;

    public R8s() {

    }

    public R8s(int id, String what, int r8, String why) {
        this._id = id;
        this._what = what;
        this._r8 = r8;
        this._why = why;
    }

    public R8s(String what, int r8, String why) {
        this._what = what;
        this._r8 = r8;
        this._why = why;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getWhat() {
        return this._what;
    }

    public void setWhat(String what) {
        this._what = what;
    }

    public int getr8() {
        return this._r8;
    }

    public void setr8(int r8) {
        this._r8 = r8;
    }

    public String getWhy() {
        return this._why;
    }

    public void setWhy(String why) {
        this._why = why;
    }
}
