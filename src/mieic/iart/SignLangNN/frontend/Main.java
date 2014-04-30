package mieic.iart.SignLangNN.frontend;

import mieic.iart.SignLangNN.backend.Intel;
import mieic.iart.SignLangNN.backend.NNFactory;
import mieic.iart.SignLangNN.backend.NNTrainer;
import mieic.iart.SignLangNN.neuralnetwork.NeuralNetwork;

/**
 * Created by wso277 on 4/14/14.
 */
public class Main {

    public static void main(String[] args) {

        Intel.getInstance().readDatabase();

        NeuralNetwork neuralNetwork = NNFactory.getNeuralNetworkModel3();

        NNTrainer trainer = new NNTrainer(neuralNetwork);
        trainer.trainNN(Intel.getInstance().getSamples());

        new Cli().menu(neuralNetwork);
    }


}
