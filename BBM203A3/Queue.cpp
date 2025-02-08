#include "Queue.h"
#include <iostream>
using namespace std;

// Constructor to initialize an empty queue
Queue::Queue() {
    front = rear = -1; // Kuyruk başlangıçta boş
}

// Adds a province to the end of the queue
void Queue::enqueue(int province) {
    if ((rear + 1) % MAX_QUEUE_SIZE == front) {
        cout << "Kuyruk dolu, eleman eklenemiyor: " << province << endl;
        return;
    }
    if (isEmpty()) {
        front = rear = 0; // İlk eleman ekleniyor
    } else {
        rear = (rear + 1) % MAX_QUEUE_SIZE; // Circular mantık
    }
    data[rear] = province;
    cout << "Eleman eklendi: " << province << endl;
}

// Removes and returns the front province from the queue
int Queue::dequeue() {
    if (isEmpty()) {
        cout << "Kuyruk boş, çıkarılacak eleman yok." << endl;
        return -1;
    }
    int removed = data[front];
    cout << removed << " kuyruktan çıkarıldı." << endl;
    if (front == rear) {
        front = rear = -1; // Kuyruk tamamen boş
    } else {
        front = (front + 1) % MAX_QUEUE_SIZE; // Circular mantık
    }
    return removed;
}

// Returns the front province without removing it
// Returns the front province without removing it
int Queue::peek() const {
    if (isEmpty()) {
        cout << "Kuyruk boş, eleman yok." << endl;
        return -1;
    }
    return data[front];
}

// Checks if the queue is empty
bool Queue::isEmpty() const {
    return front == -1;
}
// Add a priority neighboring province in a way that will be dequeued and explored before other non-priority neighbors
void Queue::enqueuePriority(int province) {
    if (((rear + 1) % MAX_QUEUE_SIZE )== front) {
        cout << "Kuyruk dolu, öncelikli eleman eklenemiyor: " << province << endl;
        return;
    }
    if (isEmpty()) {
        enqueue(province); // Kuyruk boşsa normal ekle
        return;
    }
    // Circular kuyruğun başlangıcını bir geriye kaydır
    front = (front - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
    data[front] = province;
    cout << "Öncelikli eleman eklendi: " << province << endl;
}

