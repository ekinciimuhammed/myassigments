#include "Queue.h"
#include <iostream>
using namespace std;

// Constructor to initialize an empty queue
Queue::Queue()
{
    front = rear = -1;
}

// Adds a province to the end of the queue
void Queue::enqueue(int province)
{
    if ((rear + 1) % MAX_QUEUE_SIZE == front)
    {
        cout << "Queue  is full " << province << endl;
        return;
    }
    if (isEmpty())
    {
        front = rear = 0;
    }
    else
    {
        rear = (rear + 1) % MAX_QUEUE_SIZE;
    }
    data[rear] = province;
    cout << "Element addded " << province << endl;
}

// Removes and returns the front province from the queue
int Queue::dequeue()
{
    if (isEmpty())
    {
        cout << "Queue is empty!" << endl;
        return -1;
    }
    int removed = data[front];
    cout << removed << " removed from the tail." << endl;
    if (front == rear)
    {
        front = rear = -1;
    }
    else
    {
        front = (front + 1) % MAX_QUEUE_SIZE;
    }
    return removed;
}

// Returns the front province without removing it
int Queue::peek() const
{
    if (isEmpty())
    {
        cout << "Queue is empty!" << endl;
        return -1;
    }
    return data[front];
}

// Checks if the queue is empty
bool Queue::isEmpty() const
{
    return front == -1;
}
// Add a priority neighboring province in a way that will be dequeued and explored before other non-priority neighbors
void Queue::enqueuePriority(int province)
{
    if (((rear + 1) % MAX_QUEUE_SIZE) == front)
    {
        cout << "Queue is full! " << province << endl;
        return;
    }
    if (isEmpty())
    {
        enqueue(province);
        return;
    }

    front = (front - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
    data[front] = province;
    cout << "Priority element added: " << province << endl;
}
