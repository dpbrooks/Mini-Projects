import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from sklearn.preprocessing import LabelEncoder
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import Input
from keras.utils import to_categorical
from keras.optimizers import Adam

def get_model():
    # Load dataset
    dataset = pd.read_csv('breasttissue_train.csv', header=None)

    # Split dataset into imput(X) and output(Y) variables
    # Use iloc to create arrays of data 
    X = dataset.iloc[:, 1:].values
    Y = dataset.iloc[:, 0].values

    # Instantiate MinMaxScaler
    # Use to scale data to increase performance
    minmax = MinMaxScaler()
    # Fit dataset to the proper labels (ex. category 1 = 0 index)
    # Use minmax to fit the input values to the model
    # Use to_categorical to decrement Y indexes to handle labels
    # starting at 1 instead of 0
    X = minmax.fit_transform(X)
    Y = to_categorical(Y - 1, num_classes=4)

    # Define model
    # Sequential model stacks layers of the neural net on top of eachother
    model = Sequential()
    # Define the input of the model with a shape of 9 for the
    # number of variables being assessed (10th is what is being 
    # derived)
    model.add(Input(shape=(9,)))
    # Create a Dense layer with 10 units and use relu activation
    model.add(Dense(10, activation='relu'))
    # Create another Dense layer with 4 units to match the output
    # Use softmax activation for the output layer
    model.add(Dense(4, activation='softmax'))

    # Compile model
    # categorical_crossentropy is used for multi-class classification
    # Adam is used for defining the learning rate for the neural net
    # metrics is used to determine the way the training process is evaluated
    model.compile(loss='categorical_crossentropy', optimizer=Adam(learning_rate=0.1), metrics=['accuracy'])

    # Fit model
    # Trains the model on the given data
    # Each epoch is a round of training for the model
    # batch_size determines the number of samples processed in each epoch
    # validation_split determines the percentage of data that is used to
    # validate the results from training
    model.fit(X, Y, epochs=50, batch_size=32, validation_split=0.2)

    return model
