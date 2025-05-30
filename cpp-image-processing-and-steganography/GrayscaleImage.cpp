#include "GrayscaleImage.h"
#include <iostream>
#include <cstring> // For memcpy
#define STB_IMAGE_IMPLEMENTATION
#include "stb_image.h"
#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "stb_image_write.h"
#include <stdexcept>

// Constructor: load from a file
GrayscaleImage::GrayscaleImage(const char *filename)
{
    int channels;
    unsigned char *image = stbi_load(filename, &width, &height, &channels, STBI_grey);

    if (image == nullptr)
    {
        std::cerr << "Error: Could not load image " << filename << std::endl;
        exit(1);
    }
    data = new int *[height];

    for (int i = 0; i < height; i++)
    {
        data[i] = new int[width];
    }

    for (int rowIndex = 0; rowIndex < width; rowIndex++)
    {
        for (int colIndex = 0; colIndex < height; colIndex++)
        {

            // The grayscale value is directly stored from the image data (1D array)
            data[rowIndex][colIndex] = (image[rowIndex * width + colIndex]);
        }
    }

    stbi_image_free(image);
}

GrayscaleImage::GrayscaleImage(int **inputData, int h, int w)
{

    height = h;
    width = w;

    // 1. Dynamically allocate memory for a 2D matrix based on the given dimensions
    data = new int *[height];
    for (int i = 0; i < height; i++)
    {
        data[i] = new int[width];
    }

    // 2. Copy the data from the input matrix to the newly allocated matrix
    for (int rowIndex = 0; rowIndex < width; rowIndex++)
    {
        for (int colIndex = 0; colIndex < height; colIndex++)
        {

            data[rowIndex][colIndex] = inputData[rowIndex][colIndex]; // Copying the pixel value
        }
    }
}

GrayscaleImage::GrayscaleImage(int w, int h) : width(w), height(h)
{

    // 1. Dynamically allocate memory for a 2D matrix based on the given dimensions
    data = new int *[height];
    for (int i = 0; i < height; i++)
    {
        data[i] = new int[width];
    }

    // 2. Initialize all pixel values to 0 (black in grayscale)
    for (int rowIndex = 0; rowIndex < width; rowIndex++)
    {
        for (int colIndex = 0; colIndex < height; colIndex++)
        {

            data[rowIndex][colIndex] = 0;
        }
    }
}

GrayscaleImage::GrayscaleImage(const GrayscaleImage &other)
{

    // 1. Copy the dimensions
    width = other.width;
    height = other.height;

    // 2. Dynamically allocate memory for the 2D matrix
    data = new int *[height];
    for (int i = 0; i < height; i++)
    {
        data[i] = new int[width];
    }

    // 3. Copy the pixel values from the other image
    for (int rowIndex = 0; rowIndex < width; rowIndex++)
    {
        for (int colIndex = 0; colIndex < height; colIndex++)
        {

            data[rowIndex][colIndex] = other.data[rowIndex][colIndex];
        }
    }
}

// Destructor
GrayscaleImage::~GrayscaleImage()
{

    for (int i = 0; i < width; i++)
    {
        delete[] data[i];
    }

    delete[] data;
}

// Equality operator
bool GrayscaleImage::operator==(const GrayscaleImage &other) const
{

    if (width != other.width || height != other.height)
    {
        return false;
    }
    for (int rowIndex = 0; rowIndex < width; rowIndex++)
    {
        for (int colIndex = 0; colIndex < height; colIndex++)
        {

            if (data[rowIndex][colIndex] != other.data[rowIndex][colIndex])
            {
                return false;
            }
        }
    }
    return true;
}

GrayscaleImage GrayscaleImage::operator+(const GrayscaleImage &other) const
{
    if (width != other.width || height != other.height)
    {
        throw std::invalid_argument("Images must be of the same size for addition.");
    }

    GrayscaleImage result(width, height);
    for (int rowIndex = 0; rowIndex < width; rowIndex++)
    {
        for (int colIndex = 0; colIndex < height; colIndex++)
        {

            result.data[rowIndex][colIndex] = std::min(data[rowIndex][colIndex] + other.data[rowIndex][colIndex], 255); // Clamp to 255
        }
    }
    return result;
}

GrayscaleImage GrayscaleImage::operator-(const GrayscaleImage &other) const
{

    if (width != other.width || height != other.height)
    {
        throw std::invalid_argument("Images must be of the same size for subtraction.");
    }

    GrayscaleImage result(width, height);
    for (int rowIndex = 0; rowIndex < width; rowIndex++)
    {
        for (int colIndex = 0; colIndex < height; colIndex++)
        {
            result.data[rowIndex][colIndex] = std::max(data[rowIndex][colIndex] - other.data[rowIndex][colIndex], 0); // Clamp to 0
        }
    }
    return result;
}

int GrayscaleImage::get_pixel(int row, int col) const
{
    return data[row][col];
}

void GrayscaleImage::set_pixel(int row, int col, int value)
{
    data[row][col] = value;
}

void GrayscaleImage::save_to_file(const char *filename) const
{
    unsigned char *imageBuffer = new unsigned char[width * height];

    for (int i = 0; i < width; i++)
    {
        for (int j = 0; j < height; j++)
        {
            imageBuffer[i * width + j] = static_cast<unsigned char>(data[i][j]);
        }
    }

    if (!stbi_write_png(filename, width, height, 1, imageBuffer, width))
    {
        std::cerr << "Error: Could not save image to file " << filename << std::endl;
    }

    delete[] imageBuffer;
}
