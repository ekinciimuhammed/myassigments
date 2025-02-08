#include "Map.h"
#include <fstream>
#include <iostream>
#include <sstream>

Map::Map() {
    // TODO: Your code here
    // Initialize all distances to a value representing no direct connection
    // Initialize all provinces as unvisited
    for (int i = 0; i < MAX_SIZE; ++i) {
        for (int j = 0; j < MAX_SIZE; ++j) {
            distanceMatrix[i][j] = 0; 
        }
        visited[i] = false; 
    }
}

// Loads distance data from a file and fills the distanceMatrix
void Map::loadDistanceData(const std::string& filename) {
    // TODO: Your code here
    // Read each line in the CSV file
    // Read each cell separated by a comma
    // Convert cell to an integer and store in distanceMatrix
    std::ifstream file(filename);
    if (!file.is_open()) {
        std::cerr << "The file could not be opened: " << filename << std::endl;
        return;
    }

    std::string line;
    int row = 0;

    while (std::getline(file, line) && row < MAX_SIZE) {
        std::stringstream ss(line);
        std::string cell;
        int col = 0;

        while (std::getline(ss, cell, ',') && col < MAX_SIZE) { 
            try {
                distanceMatrix[row][col] = std::stoi(cell); 
            } catch (const std::invalid_argument& e) {
                distanceMatrix[row][col] = -1;
            }
            ++col;
        }
        ++row;
    }

    file.close(); 
    
}

// Checks if the distance between two provinces is within the allowed maxDistance
bool Map::isWithinRange(int provinceA, int provinceB, int maxDistance) const {
    // TODO: Your code here
    if (provinceA < 0 || provinceA >= MAX_SIZE || provinceB < 0 || provinceB >= MAX_SIZE) {
        std::cerr << "Invalid provincial indexes: " << provinceA << " and " << provinceB << std::endl;
        return false;
    }


    int distance = distanceMatrix[provinceA][provinceB];


    return (distance != -1 && distance <= maxDistance);
}

// Marks a province as visited
void Map::markAsVisited(int province) {
        if (province < 0 || province >= MAX_SIZE) {
        std::cerr << "Invalid provincial index " << province << std::endl;
        return;
    }

    visited[province] = true;
}

// Checks if a province has already been visited
bool Map::isVisited(int province) const {

    if (province < 0 || province >= MAX_SIZE) {
        std::cerr << "Invalid provincial index " << province << std::endl;
        return false;
    }

    return visited[province];
}

// Resets all provinces to unvisited
void Map::resetVisited() {
    // TODO: Your code here
    for (int i = 0; i < MAX_SIZE; ++i) {
        visited[i] = false;
    }
}

// Function to count the number of visited provinces
int Map::countVisitedProvinces() const {
   int count = 0;
    

    for (int i = 0; i < MAX_SIZE; ++i) {
        if (visited[i]) {
            count++;  
        }
    }

    return count;
}

// Function to get the distance between two provinces
int Map::getDistance(int provinceA, int provinceB) const {
    // TODO: Your code here

    return distanceMatrix[provinceA][provinceB];
}