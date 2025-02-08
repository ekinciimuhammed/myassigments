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
            distanceMatrix[i][j] = 0; // veya belirli bir default değer
        }
        visited[i] = false; // Başlangıçta tüm şehirler ziyaret edilmemiş
    }
}

// Loads distance data from a file and fills the distanceMatrix
void Map::loadDistanceData(const std::string& filename) {
    // TODO: Your code here
    // Read each line in the CSV file
    // Read each cell separated by a comma
    // Convert cell to an integer and store in distanceMatrix
    std::ifstream file(filename); // Dosyayı aç

    if (!file.is_open()) {
        std::cerr << "Dosya açılamadı: " << filename << std::endl;
        return;
    }

    std::string line;
    int row = 0;

    while (std::getline(file, line) && row < MAX_SIZE) { // Dosyayı satır satır oku
        std::stringstream ss(line); // Satırı bir stringstream'e aktar
        std::string cell;
        int col = 0;

        while (std::getline(ss, cell, ',') && col < MAX_SIZE) { // Satırı hücrelere ayır
            try {
                distanceMatrix[row][col] = std::stoi(cell); // Hücreyi tam sayıya çevir ve matrise ata
            } catch (const std::invalid_argument& e) {
                distanceMatrix[row][col] = -1; // Geçersiz bir değer olduğunda default -1 ata
            }
            ++col;
        }
        ++row;
    }

    file.close(); // Dosyayı kapat
    
}

// Checks if the distance between two provinces is within the allowed maxDistance
bool Map::isWithinRange(int provinceA, int provinceB, int maxDistance) const {
    // TODO: Your code here
       // Geçersiz il indekslerini kontrol et
    if (provinceA < 0 || provinceA >= MAX_SIZE || provinceB < 0 || provinceB >= MAX_SIZE) {
        std::cerr << "Geçersiz il indeksleri: " << provinceA << " ve " << provinceB << std::endl;
        return false;
    }

    // Mesafeyi al
    int distance = distanceMatrix[provinceA][provinceB];

    // Eğer mesafe geçerli bir değer (örneğin -1 geçersiz bağlantıyı temsil ediyorsa) ve 
    // maxDistance'a eşit veya daha küçükse, true döndür
    return (distance != -1 && distance <= maxDistance);
}

// Marks a province as visited
void Map::markAsVisited(int province) {
        if (province < 0 || province >= MAX_SIZE) {
        std::cerr << "Geçersiz il indeksi: " << province << std::endl;
        return;
    }

    // İlin ziyaret edildiğini işaretle
    visited[province] = true;
}

// Checks if a province has already been visited
bool Map::isVisited(int province) const {
      // Geçersiz il indeksi kontrolü
    if (province < 0 || province >= MAX_SIZE) {
        std::cerr << "Geçersiz il indeksi: " << province << std::endl;
        return false;
    }

    // İl ziyaret edildiyse true, edilmediyse false döndür
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
    
    // Tüm illeri kontrol et
    for (int i = 0; i < MAX_SIZE; ++i) {
        if (visited[i]) {
            count++;  // Eğer il ziyaret edildiyse sayacı artır
        }
    }

    return count;  // Ziyaret edilen illerin sayısını döndür
}

// Function to get the distance between two provinces
int Map::getDistance(int provinceA, int provinceB) const {
    // TODO: Your code here
        // Mesafe matrisi üzerinde iki ilin mesafesini döndür
    return distanceMatrix[provinceA][provinceB];
}