package mieic.iart.SignLangNN.frontend;

import mieic.iart.SignLangNN.backend.Intel;

/**
 * Created by wso277 on 4/14/14.
 */
public class Main {

    public static void main(String[] args) {

        Intel.getInstance().readDatabase();



        Cli cli = new Cli();
    }
}
