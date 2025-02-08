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
    "Yalova", "Karabuk", "Kilis", "Osmaniye", "Duzce"};

// Constructor to initialize and load constraints
RoutePlanner::RoutePlanner(const std::string &distance_data, const std::string &priority_data, const std::string &restricted_data, int maxDistance)
    : maxDistance(maxDistance), totalDistanceCovered(0), numPriorityProvinces(0), numWeatherRestrictedProvinces(0)
{

    // TO DO:
    // Load map data from file
    // Mark all provinces as unvisited initially
    // Load priority provinces
    // Load restricted provinces
    map.loadDistanceData(distance_data);
    map.resetVisited();
    loadPriorityProvinces(priority_data);
    loadWeatherRestrictedProvinces(restricted_data);
}

// Load priority provinces from txt file to an array of indices
void RoutePlanner::loadPriorityProvinces(const std::string &filename)
{
    // TODO: Your code here
    std::ifstream file(filename);
    std::string line;
    std::regex pattern(".*\\((\\d+)\\)");

    while (std::getline(file, line) && numPriorityProvinces < MAX_PRIORITY_PROVINCES)
    {
        std::smatch matches;
        if (std::regex_search(line, matches, pattern))
        {
            int index = std::stoi(matches[1]);
            priorityProvinces[numPriorityProvinces++] = index;
        }
    }

    file.close();
}

// Load weather-restricted provinces from txt file to an array of indices
void RoutePlanner::loadWeatherRestrictedProvinces(const std::string &filename)
{
    // TODO: Your code here
    std::ifstream file(filename);

    std::string line;
    std::regex pattern(".*\\((\\d+)\\)");

    while (std::getline(file, line) && numWeatherRestrictedProvinces < MAX_WEATHER_RESTRICTED_PROVINCES)
    {
        std::smatch matches;
        if (std::regex_search(line, matches, pattern))
        {
            int index = std::stoi(matches[1]);
            weatherRestrictedProvinces[numWeatherRestrictedProvinces++] = index;
        }
    }

    file.close();
}

// Checks if a province is a priority province
bool RoutePlanner::isPriorityProvince(int province) const
{
    // TODO: Your code here
    for (int i = 0; i < numPriorityProvinces; i++)
    {
        if (priorityProvinces[i] == province)
        {
            return true;
        }
    }

    return false;
}

// Checks if a province is weather-restricted
bool RoutePlanner::isWeatherRestricted(int province) const
{
    // TODO: Your code here
    for (int i = 0; i < numWeatherRestrictedProvinces; i++)
    {
        if (weatherRestrictedProvinces[i] == province)
        {
            return true;
        }
    }
    return false;
}

// Begins the route exploration from the starting point
void RoutePlanner::exploreRoute(int startingCity)
{
    // TODO: Your code here

    if (startingCity < 0 || startingCity >= 81)
    {
        std::cerr << "Invalid starting city!" << std::endl;
        return;
    }

    map.markAsVisited(startingCity);
    route.push_back(startingCity);

    totalDistanceCovered = 0;
    exploreFromProvince(startingCity);
}
// Helper function to explore from a specific province
void RoutePlanner::exploreFromProvince(int province)
{
    // TODO: Your code here
    if (isWeatherRestricted(province))
    {
        std::cout << "Province " << cities[province] << " is weather-restricted. Skipping." << std::endl;
        map.markAsVisited(province);
    }
    if (map.isVisited(province))
    {
        return;
    }

    map.markAsVisited(province);
    route.push_back(province);

    if (route.size() > 1)
    {
        int previousProvince = route[route.size() - 2];
        totalDistanceCovered += map.getDistance(previousProvince, province);
    }

    enqueueNeighbors(province);
}

void RoutePlanner::enqueueNeighbors(int province)
{
    // TO DO: Enqueue priority & non-priority neighbors to the queue according to given constraints
    for (int i = 0; i < 81; ++i)
    {
        if (map.isWithinRange(province, i, maxDistance) && !map.isVisited(i))
        {
            if (isWeatherRestricted(i))
            {
                map.markAsVisited(i);
                continue;
            }

            if (isPriorityProvince(i))
            {
                map.markAsVisited(i);
                queue.enqueue(i);
            }
        }
    }

    for (int i = 0; i < 81; ++i)
    {
        if (map.isWithinRange(province, i, maxDistance) && !map.isVisited(i))
        {

            if (isWeatherRestricted(i))
            {
                map.markAsVisited(i);
                continue;
            }

            if (!isPriorityProvince(i))
            {
                queue.enqueue(i);
            }
        }
    }
}

void RoutePlanner::backtrack()
{
    // If you reach a dead-end province
    // TODO: Your code here
    if (!route.empty())
    {

        int lastProvince = route.back();
        route.pop_back();

        if (!stack.isEmpty())
        {
            stack.pop();
        }

        if (!route.empty())
        {
            int previousProvince = route.back();
            totalDistanceCovered -= map.getDistance(previousProvince, lastProvince);
        }
    }
}

bool RoutePlanner::isExplorationComplete() const
{
    // TODO: Your code here
    if (map.countVisitedProvinces() == MAX_SIZE)
    {
        return true;
    }

    if (queue.isEmpty() && !route.empty())
    {

        return true;
    }

    return false;
}

// loking it is working
void RoutePlanner::displayResults() const
{
    // TODO: Your code here
    // Display "Journey Completed!" message
    // Display the total number of provinces visited
    // Display the total distance covered
    // Display the route in the order visited
    // Priority Province Summary
    std::cout << "\n----------------------------" << std::endl;
    std::cout << "Journey Completed!" << std::endl;
    std::cout << "----------------------------" << std::endl;
    std::cout << "Total Number of Provinces Visited: " << route.size() << " / 81" << std::endl;

    std::cout << "Total Distance Covered: " << totalDistanceCovered << " km" << std::endl;

    std::cout << "Route Taken: ";
    for (size_t i = 0; i < route.size(); ++i)
    {
        std::cout << cities[route[i]];
        if (i != route.size() - 1)
        {
            std::cout << " -> ";
        }
    }
    std::cout << "\n"
              << std::endl;

    std::cout << "Priority Provinces Status:" << std::endl;
    int priorityVisitedCount = 0;
    for (int i = 0; i < numPriorityProvinces; ++i)
    {
        int province = priorityProvinces[i];
        std::cout << "- " << cities[province] << " (";
        if (map.isVisited(province))
        {
            std::cout << "Visited";
            ++priorityVisitedCount;
        }
        else
        {
            std::cout << "Not Visited";
        }
        std::cout << ")" << std::endl;
    }

    std::cout << "\nTotal Priority Provinces Visited: " << priorityVisitedCount
              << " out of " << numPriorityProvinces << std::endl;
    if (priorityVisitedCount == numPriorityProvinces)
    {
        std::cout << "Success: All priority provinces were visited." << std::endl;
    }
    else
    {
        std::cout << "Warning: Not all priority provinces were visited." << std::endl;
    }
}
