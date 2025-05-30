#include "RoutePlanner.h"
#include <iostream>
#include <fstream>
#include <regex>


// Array to help you out with name of the cities in order
const std::string cities[81] = {
    "Adana", "Adiyaman", "Afyon", "Agri", "Amasya", "Ankara", "Antalya", "Artvin", "Aydin", "Balikesir", "Bilecik",
    "Bingol", "Bitlis", "Bolu", "Burdur", "Bursa", "Canakkale", "Cankiri", "Corum", "Denizli", "Diyarbakir", "Edirne",
    "Elazig", "Erzincan", "Erzurum", "Eskisehir", "Gaziantep", "Giresun", "Gumushane", "Hakkari", "Hatay", "Isparta",
    "Mersin", "Istanbul", "Izmir", "Kars", "Kastamonu", "Kayseri", "Kirklareli", "Kirsehir", "Kocaeli", "Konya", "Kutahya",
    "Malatya", "Manisa", "Kaharamanmaras", "Mardin", "Mugla", "Mus", "Nevsehir", "Nigde", "Ordu", "Rize", "Sakarya",
    "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdag", "Tokat", "Trabzon", "Tunceli", "Urfa", "Usak", "Van", "Yozgat",
    "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kirikkale", "Batman", "Sirnak", "Bartin", "Ardahan", "Igdir",
    "Yalova", "Karabuk", "Kilis", "Osmaniye", "Duzce"
};

// Constructor to initialize and load constraints
RoutePlanner::RoutePlanner(const std::string& distance_data, const std::string& priority_data, const std::string& restricted_data, int maxDistance)
    : maxDistance(maxDistance), totalDistanceCovered(0), numPriorityProvinces(0), numWeatherRestrictedProvinces(0) {

    // TO DO:
    // Load map data from file
    // Mark all provinces as unvisited initially
    // Load priority provinces
    // Load restricted provinces
    map.loadDistanceData(distance_data);  // Harita verisini yükle
    map.resetVisited();
    loadPriorityProvinces(priority_data);  // Öncelikli illeri yükle
    loadWeatherRestrictedProvinces(restricted_data);  // Hava durumu kısıtlı illeri yükle

}

// Load priority provinces from txt file to an array of indices
// Load priority provinces from txt file to an array of indices
void RoutePlanner::loadPriorityProvinces(const std::string& filename) {
    // TODO: Your code here
    std::ifstream file(filename);


    std::string line;
    std::regex pattern(".*\\((\\d+)\\)");  // Regular expression to find the number inside parentheses

    while (std::getline(file, line) && numPriorityProvinces < MAX_PRIORITY_PROVINCES) {
        std::smatch matches;
        if (std::regex_search(line, matches, pattern)) {
            int index = std::stoi(matches[1]);  // Extract and convert the number inside parentheses
            priorityProvinces[numPriorityProvinces++] = index;
        }
    }

    file.close();
}

// Load weather-restricted provinces from txt file to an array of indices
void RoutePlanner::loadWeatherRestrictedProvinces(const std::string& filename) {
    // TODO: Your code here
    std::ifstream file(filename);


    std::string line;
    std::regex pattern(".*\\((\\d+)\\)");  // Regular expression to find the number inside parentheses

    while (std::getline(file, line) && numWeatherRestrictedProvinces < MAX_WEATHER_RESTRICTED_PROVINCES) {
        std::smatch matches;
        if (std::regex_search(line, matches, pattern)) {
            int index = std::stoi(matches[1]);  // Extract and convert the number inside parentheses
            weatherRestrictedProvinces[numWeatherRestrictedProvinces++] = index;
        }
    }

    file.close();

}

// Checks if a province is a priority province
bool RoutePlanner::isPriorityProvince(int province) const {
    // TODO: Your code here
    for (int i = 0; i < numPriorityProvinces; i++) {
        if (priorityProvinces[i] == province) {
            return true;
        }
    }

    return false;
}

// Checks if a province is weather-restricted
bool RoutePlanner::isWeatherRestricted(int province) const {
    // TODO: Your code here
    for (int i = 0; i < numWeatherRestrictedProvinces; i++) {
        if (weatherRestrictedProvinces[i] == province) {
            return true;
        }
    }
    return false;
}

// Begins the route exploration from the starting point
void RoutePlanner::exploreRoute(int startingCity) {
    // TODO: Your code here
      // Başlangıç şehrini kontrol et, geçerli bir şehir olup olmadığını kontrol et
    if (startingCity < 0 || startingCity >= 81) {
        std::cerr << "Geçersiz başlangıç şehri!" << std::endl;
        return;
    }

    map.markAsVisited(startingCity);
    route.push_back(startingCity);  // Başlangıç şehrini rotaya ekle

    totalDistanceCovered = 0;  // Mesafeyi sıfırla
    exploreFromProvince(startingCity);  // Keşfe başla


}
// Helper function to explore from a specific province
void RoutePlanner::exploreFromProvince(int province) {
    // TODO: Your code here
    if (isWeatherRestricted(province)) {
        std::cout << "Province " << cities[province] << " is weather-restricted. Skipping." << std::endl;
        map.markAsVisited(province);  // Ziyaret edilmiş gibi işaretle
 
    }
      if (map.isVisited(province)) {
        return;
    }

    // İli ziyaret et ve rotaya ekle
    map.markAsVisited(province);
    route.push_back(province);

    // Şu anda geçilen şehirle ilgili mesafeyi ekle
    // Komşuları gezmek için, her iki şehri birbirine bağlayan mesafeyi al
    if (route.size() > 1) {
        int previousProvince = route[route.size() - 2];
        totalDistanceCovered += map.getDistance(previousProvince, province);
    }

    // Komşu illeri kuyruğa ekle
    enqueueNeighbors(province);

}

void RoutePlanner::enqueueNeighbors(int province) {
    // TO DO: Enqueue priority & non-priority neighbors to the queue according to given constraints
 // Komşu illeri kuyruğa ekle, öncelikli illeri önce ekleyelim
    for (int i = 0; i < 81; ++i) {  // 81 şehir olduğu varsayılıyor
        if (map.isWithinRange(province, i, maxDistance) && !map.isVisited(i)) {
            // Eğer mesafe uygun ve il ziyaret edilmemişse
            if (isWeatherRestricted(i)) {
                map.markAsVisited(i);
                continue;  // Eğer hava durumu kısıtlaması varsa, geç
            }

            // Öncelikli illeri önce kuyruğa ekle
            if (isPriorityProvince(i)) 
            {
                map.markAsVisited(i);
                queue.enqueue(i);  // Öncelikli illeri kuyruğa ekle
            }
        }
    }

    // Öncelikli illeri ekledikten sonra, diğer illeri kuyruğa ekle
    for (int i = 0; i < 81; ++i) {  // 81 şehir olduğu varsayılıyor
        if (map.isWithinRange(province, i, maxDistance) && !map.isVisited(i)) {
            // Eğer mesafe uygun ve il ziyaret edilmemişse
            if (isWeatherRestricted(i)) {
                map.markAsVisited(i);
                continue;  // Eğer hava durumu kısıtlaması varsa, geç
            }

            // Diğer illeri kuyruğa ekle
            if (!isPriorityProvince(i)) {
                queue.enqueue(i);  // Diğer illeri kuyruğa ekle
            }
        }
    }
}

void RoutePlanner::backtrack() {
    // If you reach a dead-end province
    // TODO: Your code here
   if (!route.empty()) {
        // Rotadan son ili çıkart
        int lastProvince = route.back(); 
        route.pop_back();  

        // Stack'ten de aynı ili çıkar
        if (!stack.isEmpty()) {
            stack.pop();
        }

        // Eğer rota hala boş değilse, önceki ili al ve mesafeyi geri hesapla
        if (!route.empty()) {
            int previousProvince = route.back();
            totalDistanceCovered -= map.getDistance(previousProvince, lastProvince);
        }
    }
}




bool RoutePlanner::isExplorationComplete() const {
    // TODO: Your code here
    if (map.countVisitedProvinces() == MAX_SIZE) {
        return true; // Eğer tüm iller ziyaret edilmişse keşif tamamlanmıştır
    }
    
    // Diğer kontrol: Eğer kuyruğa komşu il eklenemiyorsa ve rota tamamlanmışsa da keşif tamamlanmış sayılır
    if (queue.isEmpty() && !route.empty()) {
        // Eğer kuyrukta keşfedilecek il kalmadıysa, keşif tamamlanmış olabilir
        return true;
    }

    return false; // Keşif tamamlanmamışsa false döndür

}


//loking it is working
void RoutePlanner::displayResults() const {
    // TODO: Your code here
    // Display "Journey Completed!" message
    // Display the total number of provinces visited
    // Display the total distance covered
    // Display the route in the order visited
    // Priority Province Summary
    std::cout << "\n----------------------------" << std::endl;
    std::cout << "Journey Completed!" << std::endl;
    std::cout << "----------------------------" << std::endl;

    // Toplam ziyaret edilen il sayısı
    std::cout << "Total Number of Provinces Visited: " << route.size() << " / 81" << std::endl;

    // Toplam mesafe
    std::cout << "Total Distance Covered: " << totalDistanceCovered << " km" << std::endl;

    // Ziyaret edilen rotayı göster
    std::cout << "Route Taken: ";
    for (size_t i = 0; i < route.size(); ++i) {
        std::cout << cities[route[i]];
        if (i != route.size() - 1) {
            std::cout << " -> ";
        }
    }
    std::cout << "\n" << std::endl;

    // Öncelikli illerin ziyaret durumunu listele
    std::cout << "Priority Provinces Status:" << std::endl;
    int priorityVisitedCount = 0;
    for (int i = 0; i < numPriorityProvinces; ++i) {
        int province = priorityProvinces[i];
        std::cout << "- " << cities[province] << " (";
        if (map.isVisited(province)) {
            std::cout << "Visited";
            ++priorityVisitedCount;
        } else {
            std::cout << "Not Visited";
        }
        std::cout << ")" << std::endl;
    }

    // Öncelikli iller ziyaret özeti
    std::cout << "\nTotal Priority Provinces Visited: " << priorityVisitedCount 
              << " out of " << numPriorityProvinces << std::endl;
    if (priorityVisitedCount == numPriorityProvinces) {
        std::cout << "Success: All priority provinces were visited." << std::endl;
    } else {
        std::cout << "Warning: Not all priority provinces were visited." << std::endl;
    }
}


