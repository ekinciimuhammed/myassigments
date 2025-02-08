#include "Crypto.h"
#include "GrayscaleImage.h"

std::vector<int> Crypto::extract_LSBits(SecretImage &secret_image, int message_length)
{
    std::vector<int> LSB_array;

    // 1. Reconstruct the SecretImage to a GrayscaleImage.
    GrayscaleImage image = secret_image.reconstruct();

    // 2. Calculate the image dimensions.
    int width = image.get_width();
    int height = image.get_height();
    int total_pixels = width * height;

    // 3. Determine the total bits required based on message length.
    int total_bits_needed = message_length * 7;

    // 4. Ensure the image has enough pixels; if not, throw an error.
    if (total_pixels < total_bits_needed)
    {
        throw std::invalid_argument("Image does not have enough pixels to extract the message.");
    }

    // 5. Calculate the starting pixel so the last LSB ends in the last pixel.
    int start_pixel = total_pixels - total_bits_needed;

    // 6. Extract the LSBs from the pixels.
    for (int i = start_pixel; i < total_pixels; ++i)
    {
        int pixel_value = image.get_pixel(i / width, i % width);
        LSB_array.push_back(pixel_value & 1); // Extract the least significant bit (LSB).
    }

    return LSB_array;
}

// Decrypt message by converting LSB array into ASCII characters
std::string Crypto::decrypt_message(const std::vector<int> &LSB_array)
{
    std::string message;

    // 1. Verify the LSB array size is a multiple of 7, else throw an error.
    if (LSB_array.size() % 7 != 0)
    {
        throw std::invalid_argument("Invalid LSB array size. It must be a multiple of 7.");
    }

    // 2. Convert each group of 7 bits into an ASCII character.
    for (size_t i = 0; i < LSB_array.size(); i += 7)
    {
        int ascii_value = 0;
        for (int j = 0; j < 7; ++j)
        {
            ascii_value = (ascii_value << 1) | LSB_array[i + j]; // Shift and add bit.
        }
        message.push_back(static_cast<char>(ascii_value)); // Convert to character.
    }

    // 3. Return the decrypted message.
    return message;
}

// Encrypt message by converting ASCII characters into LSBs
std::vector<int> Crypto::encrypt_message(const std::string &message)
{
    std::vector<int> LSB_array;

    // 1. Convert each character into a 7-bit binary representation.
    for (char c : message)
    {
        std::bitset<7> bits(c); // Convert character to 7-bit binary.

        // 2. Collect the bits into the LSB array.
        for (int i = 6; i >= 0; --i)
        { // Store bits in the correct order.
            LSB_array.push_back(bits[i]);
        }
    }

    // 3. Return the array of bits.
    return LSB_array;
}

SecretImage Crypto::embed_LSBits(GrayscaleImage &image, const std::vector<int> &LSB_array)
{
    // 1. Ensure the image has enough pixels to store the LSB array.
    if (image.get_width() * image.get_height() < LSB_array.size())
    {
        throw std::invalid_argument("Need a bigger image.");
    }

    int width = image.get_width();
    int height = image.get_height();
    int total_pixels = width * height;

    // 2. Find the starting pixel so the last LSB ends in the last pixel.
    int start_pixel = total_pixels - LSB_array.size();

    // 3. Iterate over the image pixels and embed the LSBs.
    int lsb_index = 0;
    for (int i = start_pixel; i < total_pixels; ++i)
    {
        int row = i / width;
        int col = i % width;

        int pixel_value = image.get_pixel(row, col);
        pixel_value = (pixel_value & ~1) | LSB_array[lsb_index++];
        image.set_pixel(row, col, pixel_value); // Update pixel value.
    }

    // 4. Return a SecretImage object constructed from the modified image.
    return SecretImage(image);
}
