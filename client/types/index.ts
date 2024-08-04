import { MouseEventHandler } from "react";

export interface CustomButtonProps {
    title: string;
    btnType?: "button" | "submit";
    containerStyles?: string;
    handleClick?: MouseEventHandler<HTMLButtonElement>;
    textStyles?: string;
    rightIcon?: string;
    isDisabled?: boolean;
}

export interface SearchManufacturerProps {
    selected: string;
    setSelected: (manufacturer: string) => void;
}

export interface CarCardProps {
    "id": number;
    "city_mpg": number;
    "class": string;
    "combination_mpg": number;
    "cylinders": number;
    "displacement": number;
    "drive": string;
    "fuel_type": string;
    "highway_mpg": number;
    "make": string;
    "model": string;
    "transmission": string;
    "year": number;
    "price": number;
    "owner": string;
}

export interface CarChangeLogProps {
    id: string;
    make: string;
    model: string;
    price: string;
    submitDate: string;
    userOwner: string;
}

export interface FeedbackCardProps {
    id: string;
    status: string;
    firstName?: string;
    lastName?: string;
    ownerEmail: string;
    satisfaction: string;
    recommendation: string;
    submitDate: string;
}

export interface UserChangeLogProps {
    email: string;
    firstName: string;
    lastName: string;
    registeredDate: string;
    role: string;
}

export interface FilterProps {
    manufacturer: string;
    year: string;
    fuel: string;
    limit: number;
    model: string;
}

export interface OptionProps {
    title: string;
    value: string;
}

export interface CustomFilterProps {
    title: string;
    options: OptionProps[];
    setFilter: (filter: any) => void;
}

export interface ShopCatalogProps {
    pageNumber: number;
    isNext: boolean;
    setLimit: (limit: number) => void;
}

export interface SearchBarProps {
    setManufacturer: (manufacturer: string) => void;
    setModel: (model: string) => void;
}

