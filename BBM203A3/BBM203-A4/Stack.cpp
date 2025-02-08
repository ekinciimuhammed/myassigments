#include "Stack.h"
#include <iostream>

// Constructor to initialize an empty stack
Stack::Stack() {
    top=-1;
    // TODO: Your code here
}

// Adds a province to the top of the stack
void Stack::push(int province) {
        if(top>=MAX_SIZE-1){
            std::cout<<"Limit exceeded ";
            return;
        }
        data[++top]=province;

    // TODO: Your code here
}

// Removes and returns the top province from the stack
int Stack::pop() {
    // TODO: Your code here
    if(top==-1){
        std::cout<<"Stack is empty \n";
        return -1;
    }
    int plaka=data[top--];
    
    return plaka;
}

// Returns the top province without removing it
int Stack::peek() const {
    // TODO: Your code here
    if (top == -1) {
        std::cout<<"Stack is empty\nn";
    }
    return data[top];

}

// Checks if the stack is empty
bool Stack::isEmpty() const {
    // TODO: Your code here
    return top==-1;
}

// Function to get the current size of the stack
int Stack::getSize() const {
    // TODO: Your code here
    return top+1;
}
