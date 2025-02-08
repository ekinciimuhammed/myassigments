#include "Filter.h"
#include <algorithm>
#include <cmath>
#include <vector>
#include <numeric>
#include <math.h>

// Mean Filter
void Filter::apply_mean_filter(GrayscaleImage& image, int kernelSize) {
    // TODO: Your code goes here.
    // 1. Copy the original image for reference.
    // 2. For each pixel, calculate the mean value of its neighbors using a kernel.
    // 3. Update each pixel with the computed mean.
    if (kernelSize <= 0 || kernelSize % 2 == 0) {
        return;
    }
    GrayscaleImage originalImage(image); 

    int offset = kernelSize / 2;
    for (int rowIndex = 0; rowIndex < image.get_width(); rowIndex++) {
        for (int colIndex = 0; colIndex < image.get_height(); colIndex++) {
       
            int sum = 0;
            int count = 0;

            for (int ky = -offset; ky <= offset; ++ky) {
                for (int kx = -offset; kx <= offset; ++kx) {
                    int neighborY = colIndex + ky;
                    int neighborX = rowIndex + kx;


                    if (neighborY >= 0 && neighborY < image.get_height() && neighborX >= 0 && neighborX < image.get_width()) {
                        sum += originalImage.get_pixel(neighborX,neighborY );
                        count++; 
                    }
                }
            }

          
            int meanValue = sum / (kernelSize*kernelSize) ; 
            image.set_pixel(rowIndex,colIndex,  meanValue);
        }
    }
}

// Gaussian Smoothing Filter
void Filter::apply_gaussian_smoothing(GrayscaleImage& image, int kernelSize, double sigma) {
    int bound = kernelSize / 2;
    int width = image.get_width();
    int height = image.get_height();
   

    double** kernel = new double*[kernelSize];
    for (int i = 0; i < kernelSize; i++) {
        kernel[i] = new double[kernelSize];
    }
    double sumk = 0.0;
    for (int i = -bound; i <= bound; i++) {
        for (int j = -bound; j <= bound; j++) {
            kernel[i + bound][j + bound] = (1 / (2 * M_PI * sigma * sigma)) *
                                                  exp(-(i * i + j * j) / (2 * sigma * sigma));
            sumk =sumk+ kernel[i + bound][j + bound];
        }
    }

    
    for (int i = 0; i < kernelSize; i++) {
        for (int j = 0; j < kernelSize; j++) {
            kernel[i][j] =kernel[i][j]/ sumk;
        }
    }


    GrayscaleImage new_image(width, height);


    for (int i = 0; i < width; i++) {
        for (int j = 0; j <height ; j++) {
            double weightedSum = 0.0;

            for (int ki = -bound; ki <= bound; ++ki) {
                for (int kj = -bound; kj <= bound; ++kj) {
                    int ni = i + ki; 
                    int nj = j + kj; 


                    int pixelValue = (ni < 0 || ni >= height || nj < 0 || nj >= width) ? 0 : image.get_pixel(ni, nj);
                    weightedSum += pixelValue * kernel[ki + bound][kj + bound];
                }
            }

   
            new_image.set_pixel(i, j, static_cast<int>(weightedSum));
        }
    }


    for (int i = 0; i <width ; i++) {
        for (int j = 0; j <height ; j++) {
            image.set_pixel(i, j, new_image.get_pixel(i, j));
        }
    }


    for (int i = 0; i < kernelSize; i++) {
        delete[] kernel[i];
    }
    delete[] kernel;
}


void Filter::apply_unsharp_mask(GrayscaleImage& image, int kernelSize, double amount) {

    GrayscaleImage blurredImage=image;
        for (int i = 0; i <image.get_width() ; i++)
        {
            for (int j = 0; j < image.get_height(); j++)
            {
            blurredImage.set_pixel(i,j,image.get_pixel(i,j));
            }
        }
    
    apply_gaussian_smoothing(blurredImage, kernelSize, 1.0); 

 
    for (int i = 0; i <image.get_width() ; i++) {
        for (int j = 0; j < image.get_height(); j++) {
            int originalValue = image.get_pixel(i, j);
            int blurredValue = blurredImage.get_pixel(i, j);
            int edgeValue = originalValue - blurredValue; 
            int sharpenedValue = originalValue +(amount * edgeValue);


            if(sharpenedValue<0){
                sharpenedValue=0;
            }
            else if(sharpenedValue>255){
                sharpenedValue=255;
            }
            image.set_pixel(i, j, sharpenedValue);
        }
    }
}
