#include "SecretImage.h"


SecretImage::SecretImage(const GrayscaleImage& image) {
    width = image.get_width();
    height = image.get_height();


    this->upper_triangular = new int[(width * (width + 1)) / 2]; 
    this->lower_triangular = new int[(width * (width - 1)) / 2]; 

    int upper_index = 0;
    int lower_index = 0;

    for (int rowIndex = 0; rowIndex < height; rowIndex++) {
        for (int colIndex = 0; colIndex < width; colIndex++) {
            if (rowIndex <= colIndex) {
                
                this->upper_triangular[upper_index] = image.get_pixel(rowIndex, colIndex);
                upper_index++;
            } else {
                
                this->lower_triangular[lower_index] = image.get_pixel(rowIndex, colIndex);
                lower_index++;
            }
        }
    }
}


SecretImage::SecretImage(int w, int h, int* upper, int* lower) {

    width = w;
    height = h;


    int upper_size = (width * (width + 1)) / 2;

    this->upper_triangular = new int[upper_size];
    

    for (int i = 0; i < upper_size; i++) {
        this->upper_triangular[i] = upper[i];  
    }


    int lower_size = (width * (width - 1)) / 2;

    this->lower_triangular = new int[lower_size];
    
  
    for (int i = 0; i < lower_size; i++) {
        this->lower_triangular[i] = lower[i];  
    }
}



SecretImage::~SecretImage() {

    delete[] upper_triangular;  
    delete[] lower_triangular;  
}


GrayscaleImage SecretImage::reconstruct() const {
 
    GrayscaleImage image(width, height);

    int upper_index = 0;  
    int lower_index = 0;  

    for (int rowIndex = 0; rowIndex < width; rowIndex++) {
        for (int colIndex = 0; colIndex < height; colIndex++) {
            if (rowIndex <= colIndex) {
              
                image.set_pixel(rowIndex, colIndex, upper_triangular[upper_index]);
                upper_index++; 
            } else {
                
                image.set_pixel(rowIndex, colIndex, lower_triangular[lower_index]);
                lower_index++;  
            }
        }
    }

    return image;  
}


void SecretImage::save_back(const GrayscaleImage& image) {

    width = image.get_width();
    height = image.get_height();


    int upper_size = (width * (width + 1)) / 2; // (n * (n + 1)) / 2

    int lower_size = (width * (width - 1)) / 2; // (n * (n - 1)) / 2


    int* new_upper = new int[upper_size];
    int* new_lower = new int[lower_size];

    int upper_index = 0;
    int lower_index = 0;

    for (int rowIndex = 0; rowIndex < width; rowIndex++) {
        for (int colIndex = 0; colIndex < height; colIndex++) {
            if (rowIndex <= colIndex) {
  
                new_upper[upper_index++] = image.get_pixel(rowIndex, colIndex);
            } else {

                new_lower[lower_index++] = image.get_pixel(rowIndex, colIndex);
            }
        }
    }


    delete[] upper_triangular;
    delete[] lower_triangular;

  
    upper_triangular = new_upper;
    lower_triangular = new_lower;
}



void SecretImage::save_to_file(const std::string& filename) {

    std::ofstream file(filename); 

    if (!file.is_open()) { 
        std::cerr << "Error opening file: " << filename << std::endl;
        return;
    }

    file << width << " " << height << std::endl;


    int upper_size = (width * (width + 1)) / 2;
    for (int i = 0; i < upper_size; i++) {
        file << upper_triangular[i];
        if (i < upper_size - 1) {
            file << " ";
        }
    }
    file << std::endl;


    int lower_size = (width * (width - 1)) / 2;
    for (int i = 0; i < lower_size; i++) {
        file << lower_triangular[i];
        if (i < lower_size - 1) {
            file << " "; 
        }
    }
    file << std::endl; 

    file.close();
}

SecretImage SecretImage::load_from_file(const std::string& filename) {

  std::ifstream file(filename); 

    if (!file.is_open()) { 
        std::cerr << "Error opening file: " << filename << std::endl;
        throw std::runtime_error("File could not be opened");
    }

  
    int w, h;
    file >> w >> h;
   
    int upper_size = (w * (w + 1)) / 2;
    int lower_size = (w * (w - 1)) / 2;

    int* upper_triangular = new int[upper_size];
    int* lower_triangular = new int[lower_size];

  
    for (int i = 0; i < upper_size; i++) {
        file >> upper_triangular[i];
    }


    for (int i = 0; i < lower_size; i++) {
        file >> lower_triangular[i];
    }


    file.close();


    SecretImage secret_image(w, h, upper_triangular, lower_triangular);
    return secret_image;
    
}



// Returns a pointer to the upper triangular part of the secret image.
int * SecretImage::get_upper_triangular() const {
    return upper_triangular;
}

// Returns a pointer to the lower triangular part of the secret image.
int * SecretImage::get_lower_triangular() const {
    return lower_triangular;
}

// Returns the width of the secret image.
int SecretImage::get_width() const {
    return width;
}

// Returns the height of the secret image.
int SecretImage::get_height() const {
    return height;
}
