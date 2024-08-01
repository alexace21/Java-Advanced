import type { Metadata } from "next";
import "./globals.css";
import { Footer, Navbar } from "@/components";
import AuthContextProvider from "@/context/AuthContext";

export const metadata: Metadata = {
  title: "72cars",
  description: "Discover the best cars in the world.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className="relative">
        <AuthContextProvider>
          <Navbar />
          {children}
          <Footer />
        </AuthContextProvider>
      </body>
    </html>
  );
}
